package com.example.lab3.api

import com.example.lab3.dto.SubjectList
import retrofit2.http.GET

interface SubjectServiceApi {

    @GET("/api/v1/subjects/")
    suspend fun getAllSubjects(): SubjectList
}
