package com.example.lab3.service

import android.content.Context
import com.example.lab3.api.RetrofitClient
import com.example.lab3.dto.CourseList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseService(val context: Context) {
    private val courseInstance = RetrofitClient.courseInstance

    fun getAllCoursesBySubjectId(subjectId: Long): CourseList {
        val call = courseInstance.getAllCurseBySubjectId(subjectId)
        var courseList: CourseList? = null
        call.enqueue(object : Callback<CourseList> {
            override fun onResponse(call: Call<CourseList>, response: Response<CourseList>) {
                courseList = response.body()
            }

            override fun onFailure(call: Call<CourseList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return courseList ?: CourseList(listOf())
    }
}