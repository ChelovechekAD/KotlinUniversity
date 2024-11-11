package com.example.lab3.dto

import com.example.lab3.enums.ErrorCode
import java.time.LocalDateTime

data class ExceptionResponse(
    val timestamp: LocalDateTime, val code: ErrorCode, val message: String,
    val path: String
)

