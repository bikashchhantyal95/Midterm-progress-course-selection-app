package com.bikash.cambrain_course_seclection_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bikash.cambrain_course_seclection_app.Database.DatabaseOfApp
import com.bikash.cambrain_course_seclection_app.databinding.ActivityCreateCourseBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCourseActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateCourseBinding
    lateinit var databaseOfApp: DatabaseOfApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        bind activityCreateCourse with the layout
        binding = ActivityCreateCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        databaseOfApp = DatabaseOfApp.getDatabase(this)//Get database instance
        binding.addCourseButton.setOnClickListener{
            //handle add course click event

                writeData() // call wirte data to add course data to database

        }
    }
    private fun writeData(){

//  get user input from the text fields
        var name = binding.courseNameTextField.text.toString()
        var code = binding.courseCodeTextField.text.toString()
        var desc = binding.courseDescriptionTextField.text.toString()
        var prof = binding.courseProfessorNameTextField.text.toString()


        //check if the fields are blank
        if (name.isNotBlank() && code.isNotBlank() && desc.isNotBlank() && prof.isNotBlank()){
            //create new course object from the user input
            val course = Course(null, name, code, desc, prof)

            //Insert course data in to database
            lifecycleScope.launch (Dispatchers.IO){
                databaseOfApp.courseDao().insertCourse(course)

            }
//            Display success message to the user
            Toast.makeText(this, "Data has been successfully added.", Toast.LENGTH_SHORT).show()
            finish() // closes the add course screen after data is added
        }else{
//            Display error message to the user if any of the fields are empty
            Toast.makeText(this, "Fields are empty.", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}