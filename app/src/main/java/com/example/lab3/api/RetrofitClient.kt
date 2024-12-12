package com.example.lab3.api

import com.example.lab3.adapter.CourseDTODeserializer
import com.example.lab3.adapter.ErrorCodeAdapter
import com.example.lab3.adapter.LocalDateTimeAdapter
import com.example.lab3.adapter.LocalTimeAdapter
import com.example.lab3.adapter.SubjectDTODeserializer
import com.example.lab3.dto.CourseDTO
import com.example.lab3.dto.SubjectDTO
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

    fun getPreConfiguredGsonBuilder(): GsonBuilder {
        return GsonBuilder()
            .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .registerTypeAdapter(ErrorCode::class.java, ErrorCodeAdapter())
    }

    private fun getRetrofitWithCustomGson(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val authInstance: AuthServiceApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    getPreConfiguredGsonBuilder().create()
                )
            )
            .build()
        retrofit.create(AuthServiceApi::class.java)
    }

    val courseInstance: CourseServiceApi by lazy {
        val retrofit = getRetrofitWithCustomGson(
            getPreConfiguredGsonBuilder()
                .registerTypeAdapter(CourseDTO::class.java, CourseDTODeserializer()).create()
        )
        retrofit.create(CourseServiceApi::class.java)
    }

    val subjectInstance: SubjectServiceApi by lazy {
        val retrofit = getRetrofitWithCustomGson(
            getPreConfiguredGsonBuilder()
                .registerTypeAdapter(SubjectDTO::class.java, SubjectDTODeserializer()).create()
        )
        retrofit.create(SubjectServiceApi::class.java)
    }

    val courseGroupInstance: CourseGroupServiceApi by lazy {
        val retrofit = getRetrofitWithCustomGson(getPreConfiguredGsonBuilder().create())
        retrofit.create(CourseGroupServiceApi::class.java)
    }
}