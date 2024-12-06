package com.example.lab3.api

import com.example.lab3.dto.CourseGroupList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseGroupServiceApi {

    @GET("/api/v1/courses/groups/")
    fun getAllCurseGroupByCurseId(@Query(value = "curseId") cursesId: Long): Call<CourseGroupList>
}