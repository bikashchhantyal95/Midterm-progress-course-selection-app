package com.bikash.cambrain_course_seclection_app.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bikash.cambrain_course_seclection_app.Course

@Database(entities = [Course::class], version = 1)
abstract class DatabaseOfApp: RoomDatabase() {

    abstract fun courseDao(): CourseDAO
   companion object{
       @Volatile
       private var INSTANCE : DatabaseOfApp? = null


       // create a method to get instance of the database
       fun getDatabase(context: Context): DatabaseOfApp{
           //get the current instance if exists
           val tempInstance = INSTANCE

           if(tempInstance != null){
               return tempInstance
           }
           //create new instance if it does not exists
           synchronized(this){
               val instance  = Room.databaseBuilder(
                   context.applicationContext,
                   DatabaseOfApp::class.java,
                   "course_database"
               ).build()
               INSTANCE = instance
               return instance
           }
       }
   }
}

