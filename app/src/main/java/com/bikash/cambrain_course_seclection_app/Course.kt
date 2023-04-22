package com.bikash.cambrain_course_seclection_app

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NonNls

@Entity(tableName = "Course")
data class Course(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @NonNull @ColumnInfo(name = "code") val code: String,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @NonNull @ColumnInfo(name = "description") val description: String,
    @NonNull @ColumnInfo(name = "professor") val professor: String) {

}