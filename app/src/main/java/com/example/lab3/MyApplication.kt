package com.example.lab3

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.lab3.configuration.AppDatabase

class MyApplication : Application() {

    lateinit var db: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        Log.d("MyApplication", "onCreate called")

        instance = this
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()
        Log.d("MyApplication", "Database initialized")
    }

    companion object {
        lateinit var instance: MyApplication

        fun getDatabase(): AppDatabase = instance.db
    }
}