package com.bikash.cambrain_course_seclection_app

import android.os.Parcelable

class User(var name: String, var email: String, var program: String, var semester: String){
    var myCourses = mutableListOf<Course>()

    fun addCourse(course: Course){
        myCourses.add(course)
    }

    override fun toString(): String {
        return "User(name='$name', email='$email', program='$program', semester='$semester', myCourses=$myCourses)"
    }
}