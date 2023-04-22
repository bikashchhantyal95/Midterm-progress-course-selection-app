package com.bikash.cambrain_course_seclection_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bikash.cambrain_course_seclection_app.Database.CourseViewModel
import com.bikash.cambrain_course_seclection_app.Database.DatabaseOfApp
import com.bikash.cambrain_course_seclection_app.databinding.ActivityCourseListBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CourseListActivity : AppCompatActivity() {
    lateinit var binding: ActivityCourseListBinding
    lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCourseListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //show action bar
        var actionBar = supportActionBar

        gson = Gson()

        //        check if action bar is not null
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add Course"
        }

        //        create instance of view model to read data from database
        val viewModel = ViewModelProvider(this).get(CourseViewModel::class.java)
        viewModel.readAllCourseData.observe(this, Observer {
            courses ->
                val list_courses = courses.toMutableList()
            val adapter = ListViewAdapter(this, list_courses)
            binding.listCourses.adapter = adapter
            binding.listCourses.onItemClickListener = AdapterView.OnItemClickListener{
                    parent, view, position, id ->
                val modalDialog = LayoutInflater.from(this).inflate(R.layout.modal_view_course,null)

                modalDialog.findViewById<Button>(R.id.add_to_user_list).setOnClickListener{
                    val selectedCourse = courses[position]

                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("course_selected",gson.toJson(selectedCourse))
                    Log.d("user course json", intent.toString())
                    setResult(Activity.RESULT_OK, intent)


                //     val newB = AlertDialog.Builder(this).setTitle("${selectedCourse.name}")
                //   newB.show()
                }
                val builder =AlertDialog.Builder(this)
                    .setView(modalDialog)
                    .setTitle("Details")
               modalDialog.findViewById<TextView>(R.id.courseName).text = courses[position].name
                modalDialog.findViewById<TextView>(R.id.courseCode).text = "(${courses[position].code})"
                modalDialog.findViewById<TextView>(R.id.courseDesc).text = courses[position].description
                modalDialog.findViewById<TextView>(R.id.CourseProf).text = "prof.${courses[position].professor}"
                try{
                    builder.show()
                }catch (e: WindowManager.BadTokenException){
                    Log.d("CourseListActivity","Invalid token.")
                }
            }
        })
    }
}