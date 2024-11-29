package com.example.lab3

import android.app.Application
import androidx.room.Room
import com.example.lab3.configuration.AppDatabase
import com.example.lab3.service.AuthService

class MyApplication : Application() {

    lateinit var db: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()
        val authService = AuthService(this)
        authService.checkAuth()
    }

    companion object {
        lateinit var instance: MyApplication

        fun getDatabase(): AppDatabase = instance.db
    }
}