package com.example.lab3.service

import android.content.Context
import com.example.lab3.api.RetrofitClient
import com.example.lab3.dto.CourseList

class CourseService(val context: Context) {
    private val courseInstance = RetrofitClient.courseInstance

    suspend fun getAllCoursesBySubjectId(subjectId: Long): CourseList {
        return try {
            courseInstance.getAllCurseBySubjectId(subjectId)
        } catch (e: Exception) {
            e.printStackTrace()
            CourseList(listOf())
        }
    }
}