package com.bikash.cambrain_course_seclection_app.Database

import androidx.lifecycle.LiveData
import com.bikash.cambrain_course_seclection_app.Course

class CourseRepository(private val courseDAO: CourseDAO) {
    val readAllDatabaseData: LiveData<List<Course>> = courseDAO.getAll()
}