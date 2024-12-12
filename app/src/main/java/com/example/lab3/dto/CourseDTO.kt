package com.example.lab3.dto

data class CourseDTO(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String = ""
)