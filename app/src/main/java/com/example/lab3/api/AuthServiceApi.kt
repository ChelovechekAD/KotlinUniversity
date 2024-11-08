package com.example.lab3.api

import com.example.lab3.dto.LoginRequest
import com.example.lab3.dto.RegistrationRequest
import com.example.lab3.dto.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthServiceApi {

    @POST("/api/v1/auth/registration")
    fun regUser(@Body data: RegistrationRequest): Call<Void>

    @POST("/api/v1/auth/login")
    fun loginUser(@Body data: LoginRequest): Call<UserDTO>

    @DELETE("/api/v1/user/")
    fun deleteUserByLogin(@Path(value = "userLogin") login: String): Call<Void>
}
