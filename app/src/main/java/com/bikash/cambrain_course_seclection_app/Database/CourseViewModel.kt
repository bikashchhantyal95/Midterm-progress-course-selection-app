package com.bikash.cambrain_course_seclection_app.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.bikash.cambrain_course_seclection_app.Course

abstract class CourseViewModel(application: Application): AndroidViewModel(application) {
     val readAllCourseData : LiveData<List<Course>>
     private val courseRepo: CourseRepository

    init {
    val courseDAO = DatabaseOfApp.getDatabase(application).courseDao()

        courseRepo = CourseRepository(courseDAO)
        readAllCourseData = courseRepo.readAllDatabaseData
    }

}