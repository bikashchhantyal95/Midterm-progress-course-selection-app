package com.bikash.cambrain_course_seclection_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bikash.cambrain_course_seclection_app.databinding.ActivityCourseListBinding

class CourseListActivity : AppCompatActivity() {
    lateinit var courseList: CourseList
    lateinit var binding: ActivityCourseListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCourseListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        courseList = CourseList()

        //JAV-1001 - 11354 - App Development for Android
        //val courseList  = mutableListOf<Course>()
        //ISP-1002 - 11344 - App Development for iOS
        courseList.addCourse(Course("CS1001","Computer Science","This is computer","John Dalton"))
        courseList.addCourse(Course("JAV1001","App Development for Android","This is Andriod Course.","Graham Gibson"))
        courseList.addCourse(Course("WEB1001","Web Development", "This is Web development","Brent Riche"))
        courseList.addCourse(Course("ISP1002", "IOS Development","Students will create, build, debug, and test applications for the iOS platform.","Joshua Van der Most"))
        courseList.addCourse(Course("BTA1002","Foundations of B.A.","current best practices in guiding people through constant change are discussed.","Saifur Rehman"))
        courseList.addCourse(Course("DAB1000","Structured Data Management","students will explore the data warehouse lifecycle and dimensional modelling for data warehousing.","Samay Bhavsar"))


        val adapter = ListViewAdapter(this, courseList)
        binding.listCourses.adapter = adapter



    }
}