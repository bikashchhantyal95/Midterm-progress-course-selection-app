package com.bikash.cambrain_course_seclection_app.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bikash.cambrain_course_seclection_app.Course

@Dao
interface CourseDAO {
    @Query("SELECT * FROM Course ORDER by name ASC") //select the courses from the database as live data
    fun getAll(): LiveData<List<Course>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // insert the new course on the table
    fun insertCourse(course: Course)

    @Delete // delete existing course from table
    fun deleteCourse(course: Course)


}