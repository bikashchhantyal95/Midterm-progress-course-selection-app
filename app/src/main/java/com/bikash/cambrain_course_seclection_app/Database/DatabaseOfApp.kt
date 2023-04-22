package com.bikash.cambrain_course_seclection_app.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bikash.cambrain_course_seclection_app.Course

@Database(entities = [Course::class], version = 1)
abstract class DatabaseOfApp: RoomDatabase() {

   companion object{
       private var INSTANCE : DatabaseOfApp? = null

       fun getDatabase(context: Context): DatabaseOfApp{
           val tempInstance = INSTANCE

           if(tempInstance != null){
               return tempInstance
           }
           synchronized(this){
               val instance  =Room.databaseBuilder(
                   context.applicationContext,
                   DatabaseOfApp::class.java,
                   "course_selection_database"
               ).build()
               INSTANCE = instance
               return instance
           }
       }
   }
}