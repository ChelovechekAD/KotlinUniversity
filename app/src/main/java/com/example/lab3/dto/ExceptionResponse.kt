package com.example.lab3.dto

import java.time.LocalDateTime

data class ExceptionResponse(val timestamp: LocalDateTime, val status: String, val message: String,
                             val path: String)

