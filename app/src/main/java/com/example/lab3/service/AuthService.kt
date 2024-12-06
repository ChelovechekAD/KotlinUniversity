package com.example.lab3.service

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.lab3.activity.AuthActivity
import com.example.lab3.activity.MainActivity
import com.example.lab3.api.RetrofitClient
import com.example.lab3.dto.LoginRequest
import com.example.lab3.dto.RegistrationRequest
import com.example.lab3.dto.UserDTO
import com.example.lab3.mapper.UserMapper
import com.example.lab3.model.User
import com.example.lab3.util.ExceptionResponseParser
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(DelicateCoroutinesApi::class)
class AuthService(val context: Context) {

    private val instance = RetrofitClient.authInstance;

    private val userService = UserService(context)

    fun checkAuth(): Boolean {
        val sharedPref = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
        println(isLoggedIn)
        return isLoggedIn
    }

    fun regUser(request: RegistrationRequest) {
        GlobalScope.launch(Dispatchers.IO) {
            val call = instance.regUser(request);
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "User created successfully!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        proceedErrorResponse(response)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    onFailure(t.message)
                }
            })
        }
    }


    fun loginUser(request: LoginRequest) {
        GlobalScope.launch(Dispatchers.IO) {
            val call = instance.loginUser(request)
            call.enqueue(object : Callback<UserDTO> {
                override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                    if (response.isSuccessful) {
                        val userDTO = response.body()
                        Toast.makeText(
                            context, "User login successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
//                        val message = """
//                        ID: ${userDTO?.id}
//                        Login: ${userDTO?.login}
//                        Email: ${userDTO?.email}
//                        Phone: ${userDTO?.phone}
//                    """.trimIndent()
//                        AlertDialog.Builder(context)
//                            .setTitle("Login Information")
//                            .setMessage(message)
//                            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
//                            .show()
                        if (userDTO != null) {
                            val user: User = UserMapper.toEntity(userDTO)
                            println(user)
                            GlobalScope.launch(Dispatchers.IO) {
                                userService.saveUserInformation(user)
                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(context, intent, null)
                            }
                        }
                    } else {
                        proceedErrorResponse(response)
                    }
                }

                override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                    onFailure(t.message)
                }
            })
        }
    }

    fun deleteUserByLogin(login: String) {
        GlobalScope.launch(Dispatchers.IO) {
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
                    onFailure(t.message)
                }
            })
        }
    }

    fun logout() {
        GlobalScope.launch(Dispatchers.IO) {
            userService.deleteUserFromLocalDB()
            val intent = Intent(context, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(context, intent, null)
        }
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

    private fun onFailure(message: String?) {
        Toast.makeText(
            context, "Request failed: ${message ?: "Something went wrong!"}",
            Toast.LENGTH_SHORT
        ).show()
    }
}