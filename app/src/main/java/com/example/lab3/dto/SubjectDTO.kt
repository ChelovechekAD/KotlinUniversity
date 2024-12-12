package com.example.lab3.dto

data class SubjectDTO(
    val id: Long, val subjectName: String, var courseList: List<CourseDTO>,
    var isExpanded: Boolean
)
