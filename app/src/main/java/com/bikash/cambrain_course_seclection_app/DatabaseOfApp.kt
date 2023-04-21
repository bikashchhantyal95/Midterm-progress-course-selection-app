package com.bikash.cambrain_course_seclection_app

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Course::class], version = 1)
abstract class DatabaseOfApp: RoomDatabase() {

}