package com.bikash.cambrain_course_seclection_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bikash.cambrain_course_seclection_app.Database.CourseViewModel
import com.bikash.cambrain_course_seclection_app.Database.DatabaseOfApp
import com.bikash.cambrain_course_seclection_app.databinding.ActivityCourseListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CourseListActivity : AppCompatActivity() {
    lateinit var binding: ActivityCourseListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCourseListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(CourseViewModel::class.java)
        viewModel.readAllCourseData.observe(this, Observer {
            courses ->
                val list_courses = courses.toMutableList()
            val adapter = ListViewAdapter(this, list_courses)
            binding.listCourses.adapter = adapter

        })
    }
}