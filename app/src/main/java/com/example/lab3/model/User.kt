package com.example.lab3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val id: UUID,
    val login: String,
    val email: String,
    val phone: String
)