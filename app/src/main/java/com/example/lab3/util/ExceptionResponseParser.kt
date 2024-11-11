package com.example.lab3.util

import com.example.lab3.api.RetrofitClient
import com.example.lab3.dto.ExceptionResponse
import com.google.gson.JsonSyntaxException
import retrofit2.Response

class ExceptionResponseParser {

    companion object {

        fun <T> parseToExceptionResponse(response: Response<T>): ExceptionResponse? {
            val errorBody = response.errorBody()?.string()
            if (errorBody.isNullOrEmpty()) {
                return null
            }
            val gson = RetrofitClient.gson
            return try {
                gson.fromJson(errorBody, ExceptionResponse::class.java)
            } catch (e: JsonSyntaxException) {
                null
            }
        }
    }
}