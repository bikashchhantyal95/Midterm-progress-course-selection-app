package com.bikash.cambrain_course_seclection_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        binding = ActivityCreateCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseOfApp = DatabaseOfApp.getDatabase(this)
        binding.addCourseButton.setOnClickListener{
            var name = binding.courseNameTextField.text.toString()
            var code = binding.courseCodeTextField.text.toString()
            var desc = binding.courseDescriptionTextField.text.toString()
            var prof = binding.courseProfessorNameTextField.text.toString()

            if (name.isNotBlank() && code.isNotBlank() && desc.isNotBlank() && prof.isNotBlank()){
                val course = Course(null, name, code, desc, prof)
                Log.d("course","${course}")
                writeData(course)
            }
        }
    }
    private fun writeData(course: Course){
        lifecycleScope.launch (Dispatchers.IO){
            databaseOfApp.courseDao().insertCourse(course)
            finish() // closes the add contact screen after data is added
        }
        Toast.makeText(this, "Data has been successfully added.", Toast.LENGTH_SHORT).show()
    }
}