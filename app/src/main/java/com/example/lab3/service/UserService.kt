package com.example.lab3.service

import android.content.Context
import com.example.lab3.MyApplication
import com.example.lab3.configuration.AppDatabase
import com.example.lab3.model.User
import java.util.UUID

class UserService(private val context: Context) {

    private val db: AppDatabase = MyApplication.getDatabase()

    suspend fun saveUserInformation(user: User) {
        db.userDao().getUserById(user.id) ?: db.userDao().insertUser(user)
        val sharedPref = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("userId", user.id.toString())
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    suspend fun deleteUserFromLocalDB() {
        val sharedPref = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val user = getUserInformation()
        if (user != null) {
            db.userDao().deleteUser(user)
        }
        sharedPref.edit().clear().apply()
    }

    suspend fun getUserInformation(): User? {
        val sharedPref = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val userIdStr: String? = sharedPref.getString("userId", null)
        if (userIdStr != null) {
            val userId: UUID = UUID.fromString(userIdStr)
            return db.userDao().getUserById(userId)
        }
        return null
    }

}