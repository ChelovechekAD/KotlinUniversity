package com.example.lab3.service

import android.content.Context
import com.example.lab3.api.RetrofitClient
import com.example.lab3.dto.CourseGroupList

class CourseGroupService(var context: Context) {
    private val courseGroupInstance = RetrofitClient.courseGroupInstance

    suspend fun getAllCourseGroupsByCourseId(courseId: Long): CourseGroupList {
        return try {
            courseGroupInstance.getAllCurseGroupByCurseId(courseId)
        } catch (e: Exception) {
            e.printStackTrace()
            CourseGroupList(listOf())
        }
    }
}