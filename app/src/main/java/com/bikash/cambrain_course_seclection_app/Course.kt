package com.bikash.cambrain_course_seclection_app

import androidx.room.Entity


data class Course(val code: String, val name: String, val description: String, val professor: String) {
    override fun toString(): String {
        return "$code : $name"
    }
}