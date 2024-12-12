package com.example.lab3.dto

import java.time.DayOfWeek
import java.time.LocalTime

data class CourseGroupDTO(
    val id: Long,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val daysOfWeek: Set<DayOfWeek>
)
