package com.example.lab3.api

import android.provider.ContactsContract.Data
import com.example.lab3.dto.CreateUserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserServiceApi {

    @POST("/api/v1/user")
    fun createUser(@Body data: CreateUserRequest): Call<Void>
}
