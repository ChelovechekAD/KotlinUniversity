package com.example.lab3.api

import com.example.lab3.adapter.ErrorCodeAdapter
import com.example.lab3.adapter.LocalDateTimeAdapter
import com.example.lab3.enums.ErrorCode
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8090/"

    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .registerTypeAdapter(ErrorCode::class.java, ErrorCodeAdapter())
        .create()

    val instance: AuthServiceApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(AuthServiceApi::class.java)
    }
}