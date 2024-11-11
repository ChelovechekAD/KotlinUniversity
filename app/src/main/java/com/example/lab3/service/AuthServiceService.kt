package com.example.lab3.service

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.lab3.api.RetrofitClient
import com.example.lab3.dto.LoginRequest
import com.example.lab3.dto.RegistrationRequest
import com.example.lab3.dto.UserDTO
import com.example.lab3.util.ExceptionResponseParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthServiceService(val context: Context) {

    private val instance = RetrofitClient.instance;

    fun regUser(request: RegistrationRequest) {
        val call = instance.regUser(request);
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "User created successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    proceedErrorResponse(response)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(
                    context, "Request failed: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun loginUser(request: LoginRequest) {
        val call = instance.loginUser(request)
        call.enqueue(object : Callback<UserDTO> {
            override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    Toast.makeText(
                        context, "User login successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    val message = """
                        ID: ${user?.id}
                        Login: ${user?.login}
                        Email: ${user?.email}
                        Phone: ${user?.phone}
                    """.trimIndent()
                    AlertDialog.Builder(context)
                        .setTitle("Login Information")
                        .setMessage(message)
                        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        .show()
                } else {
                    proceedErrorResponse(response)
                }
            }

            override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                Toast.makeText(
                    context, "Request failed: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun deleteUserByLogin(login: String) {
        val call = instance.deleteUserByLogin(login)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        context, "User successfully deleted!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    proceedErrorResponse(response)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(
                    context, "Request failed: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun <T> proceedErrorResponse(response: Response<T>) {
        val exceptionResponse = ExceptionResponseParser.parseToExceptionResponse(response)
        if (exceptionResponse != null) {
            Toast.makeText(context, exceptionResponse.message, Toast.LENGTH_LONG).show()
            return
        } else {
            Toast.makeText(
                context, "Server error: ${response.code()}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}