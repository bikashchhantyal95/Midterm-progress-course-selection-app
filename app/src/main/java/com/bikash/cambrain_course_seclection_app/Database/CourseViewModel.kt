package com.bikash.cambrain_course_seclection_app.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.bikash.cambrain_course_seclection_app.Course

abstract class CourseViewModel(application: Application): AndroidViewModel(application) {

    //list the data in database
     val readAllCourseData : LiveData<List<Course>>

     //provide access to course data in database
     private val courseRepo: CourseRepository

     //initialize course repo and read all course data
    init {
    val courseDAO = DatabaseOfApp.getDatabase(application).courseDao()

        courseRepo = CourseRepository(courseDAO)
        readAllCourseData = courseRepo.readAllDatabaseData
    }

}