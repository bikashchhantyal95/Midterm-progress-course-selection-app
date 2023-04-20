package com.bikash.cambrain_course_seclection_app

class User(var name: String, var email: String, var program: String, var semester: String) {
    private var myCourses = mutableListOf<Course>()

    fun addCourse(course: Course){
        myCourses.add(course)
    }

    fun getUserCourseList(): List<Course>{
        return myCourses
    }
    override fun toString(): String {
        return "User(name='$name', email='$email', program='$program', semester='$semester', myCourses=$myCourses)"
    }
}