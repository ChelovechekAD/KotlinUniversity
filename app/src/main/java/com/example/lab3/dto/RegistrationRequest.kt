package com.example.lab3.dto

data class RegistrationRequest(
    val login: String,
    val password: String,
    val email: String,
    val phone: String
)