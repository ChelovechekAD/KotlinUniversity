package com.example.lab3.service

import android.content.Context
import com.example.lab3.MyApplication
import com.example.lab3.configuration.AppDatabase
import com.example.lab3.model.User

class UserService(val context: Context) {

    private val db: AppDatabase = MyApplication.getDatabase()

    suspend fun saveUserInformation(user: User) {
        db.userDao().insertUser(user)
        val sharedPref = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("userId", user.id.toString())
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }


}