package com.bikash.cambrain_course_seclection_app

import android.content.Context
import android.database.DataSetObserver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bikash.cambrain_course_seclection_app.databinding.ListItemBinding


class ListViewAdapter(val context: Context, val courseList: CourseList): BaseAdapter(){
    override fun getCount(): Int {
        return courseList.getCourses().size
    }

    override fun getItem(position: Int): Any {
        return courseList.getCourses()[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView
        if(myConvertView == null){
            myConvertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val course = getItem(position) as Course
        val courseCode = myConvertView?.findViewById<TextView>(R.id.course_id)
        val courseName = myConvertView?.findViewById<TextView>(R.id.course_name)
        val courseProf = myConvertView?.findViewById<TextView>(R.id.course_prof)

        courseCode?.text = course.code
        courseName?.text = course.name
        courseProf?.text = "prof:${course.professor}"

        return  myConvertView!!
    }

}