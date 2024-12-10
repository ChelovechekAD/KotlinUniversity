package com.example.lab3.api

import com.example.lab3.dto.CourseGroupList
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseGroupServiceApi {

    @GET("/api/v1/courses/groups/")
    suspend fun getAllCurseGroupByCurseId(@Query(value = "courseId") cursesId: Long): CourseGroupList
}