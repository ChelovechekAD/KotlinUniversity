package com.example.lab3.api

import com.example.lab3.dto.CourseList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseServiceApi {

    @GET("/api/v1/courses/")
    fun getAllCurseBySubjectId(@Query(value = "subjectId") subjectId: Long): Call<CourseList>
}