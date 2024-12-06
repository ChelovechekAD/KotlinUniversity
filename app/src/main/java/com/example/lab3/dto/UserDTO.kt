package com.example.lab3.dto

import java.util.UUID

data class UserDTO(
    val id: UUID,
    val login: String,
    val email: String,
    val password: String,
    val phone: String
)
