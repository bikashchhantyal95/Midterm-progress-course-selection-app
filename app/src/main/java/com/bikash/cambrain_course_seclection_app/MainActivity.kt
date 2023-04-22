package com.bikash.cambrain_course_seclection_app



import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bikash.cambrain_course_seclection_app.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var user: User? = null
    lateinit var gson:Gson
    //late-init var preferences: SharedPreferences
    val EDIT_USER_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // save default user data
        saveInitialData(this)

        setContentView(binding.root)
//        initialize gson property
        gson = Gson()


        binding.apply {
//            User Info
            userName.text = user?.name
            userEmail.text = user?.email
            userProgram.text = user?.program

            editBtn.setOnClickListener{ //on click listener for add btn

                // intent
                val intent = Intent(this@MainActivity, UserEditActivity::class.java)

                // send user data to UserEditActivity
                intent.putExtra("user", gson.toJson(user))
                startActivityForResult(intent, EDIT_USER_REQUEST_CODE)
//                startActivity(intent)
            }

        }

        binding.btnAddCourse.setOnClickListener{
            val intent = Intent(this@MainActivity, CourseListActivity::class.java)
            startActivity(intent)
        }

        binding.btnCreateCourse.setOnClickListener{
            val intent = Intent(this@MainActivity, CreateCourseActivity::class.java)
            startActivityForResult(intent, EDIT_USER_REQUEST_CODE)
            startActivity(intent)
        }
       // val adapter = ListViewAdapter(this,user.getUserCourseList())
        //binding.userSelectedCourseList.adapter = adapter

        val pref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val userJson = pref.getString("default_user", null)
//        if(userJson != null){
//            user = gson.fromJson(userJson, User::class.java)
//        }

//        val adapter = user?.myCourses?.let { ListViewAdapter(this, it) }
//        Log.d("user","${user?.myCourses.toString()}")
//        binding.userSelectedCourseList.adapter = adapter
    }


    fun saveInitialData(context:Context){
        val prefs = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        val gson = Gson()
        val default_user = prefs.getString("default_user", null)

        user = if(default_user != null){
            gson.fromJson(default_user, User::class.java)
        }else{
            user = User("John Doe",
                "doeJohn@gmail.com",
                "Computer Science",
                "first semester")
            val userJson = gson.toJson(user)
            prefs.edit().putString("default_user", userJson).apply()
            user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val updateUserJson = data?.getStringExtra("updated_user")
            if (updateUserJson != null) {
                val updatedUser = gson.fromJson(updateUserJson, User::class.java)

                binding.apply {
                    userName.text = updatedUser.name
                    userEmail.text = updatedUser.email
                    userProgram.text = updatedUser.program
                }
//                get shared pref
                val pref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)


                val editor = pref.edit() //get editor to make changes
                val updateUserString = gson.toJson(updatedUser) // convert to json string
                editor.putString("default_user", updateUserString) // update the key
                editor.apply() //apply the changes

            }

            val selectedCourseJson = data?.getStringExtra("course_selected")
            //Log.d("course on main", "${selectedCourseJson}")
            //course_selected

            if (selectedCourseJson != null) {
                val selectedCourse = gson.fromJson(selectedCourseJson, Course::class.java)
                val prefs = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val userJsonString = prefs.getString("default_user", null)

                val gson = Gson()
                val getUser = gson.fromJson(userJsonString, User::class.java)
                Log.d("user with course","${getUser.myCourses.toString()}")
                //Log.d("user course", getUser.getUserCourseList().toString())
                getUser.addCourse(selectedCourse)

                val updatedUser = gson.toJson(getUser)
                prefs.edit().putString("default_user", updatedUser).apply()

           }
            }
        }

}