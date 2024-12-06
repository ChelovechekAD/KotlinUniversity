package com.example.lab3.api

import com.example.lab3.adapter.ErrorCodeAdapter
import com.example.lab3.adapter.LocalDateTimeAdapter
import com.example.lab3.adapter.LocalTimeAdapter
import com.example.lab3.enums.ErrorCode
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.LocalTime

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8090/"

    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .registerTypeAdapter(ErrorCode::class.java, ErrorCodeAdapter())
        .create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val authInstance: AuthServiceApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        retrofit.create(AuthServiceApi::class.java)
    }

    val courseInstance: CourseServiceApi by lazy {
        retrofit.create(CourseServiceApi::class.java)
    }

    val subjectInstance: SubjectServiceApi by lazy {
        retrofit.create(SubjectServiceApi::class.java)
    }

    val courseGroupInstance: CourseGroupServiceApi by lazy {
        retrofit.create(CourseGroupServiceApi::class.java)
    }
}