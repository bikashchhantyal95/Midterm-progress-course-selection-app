package com.bikash.cambrain_course_seclection_app

class CourseList {
    private val courseList = mutableListOf<Course>()

    fun addCourse(course: Course){
        courseList.add(course)
    }

    fun deleteCourse(course: Course){
        courseList.remove(course)
    }

    fun getCourses():List<Course>{
        return  courseList
    }
}