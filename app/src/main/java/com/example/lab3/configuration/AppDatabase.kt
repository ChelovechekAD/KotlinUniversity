package com.example.lab3.configuration

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab3.dao.UserDAO
import com.example.lab3.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}