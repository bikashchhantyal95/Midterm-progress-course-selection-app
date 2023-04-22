package com.bikash.cambrain_course_seclection_app



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bikash.cambrain_course_seclection_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var courseList: CourseList
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        user = User("John Doe", "doeJohn@gmail.com", "Computer Science", "first semester")
        setContentView(binding.root)

        binding.apply {
//            User Info
            userName.text = user.name
            userEmail.text = user.email
            userProgram.text = user.program

            editBtn.setOnClickListener{
                val intent = Intent(this@MainActivity, UserEditActivity::class.java)
                startActivity(intent)
            }

        }
        binding.btnAddCourse.setOnClickListener{
            val intent = Intent(this@MainActivity, CourseListActivity::class.java)
            startActivity(intent)
        }
        binding.btnCreateCourse.setOnClickListener{
            val intent = Intent(this@MainActivity, CreateCourseActivity::class.java)
            startActivity(intent)
        }
    }


}