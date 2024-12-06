package com.example.lab3.service

import android.content.Context
import com.example.lab3.api.RetrofitClient
import com.example.lab3.dto.SubjectList

class SubjectService(val context: Context) {
    private val subjectInstance = RetrofitClient.subjectInstance

    suspend fun getAllSubjects(): SubjectList {
        return try {
            subjectInstance.getAllSubjects()
        } catch (e: Exception) {
            e.printStackTrace()
            SubjectList(listOf())
        }
    }
}