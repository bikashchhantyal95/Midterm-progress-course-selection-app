package com.bikash.cambrain_course_seclection_app

class CourseList {
    private val courseList = mutableListOf<Course>()

//    init {
//        // Add some dummy courses
//        addCourse(Course("CS101", "Introduction to Computer Science", "An overview of computer science and programming concepts", "Dr. Smith"))
//        addCourse(Course("ENG201", "English Literature", "A survey of literature from the Romantic era to modern times", "Prof. Jones"))
//    }

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