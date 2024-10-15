package com.example.lab3.service

import android.content.Context
import android.widget.Toast
import com.example.lab3.api.RetrofitClient
import com.example.lab3.dto.CreateUserRequest
import kotlinx.coroutines.currentCoroutineContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService(val context: Context) {

    private val instance = RetrofitClient.instance;

    fun createUser(request: CreateUserRequest) {
        val call = instance.createUser(request);
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "User saved successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Server error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, "Request failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}