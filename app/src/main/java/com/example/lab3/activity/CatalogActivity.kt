package com.example.lab3.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.adapter.SubjectAdapter
import com.example.lab3.dto.SubjectDTO
import com.example.lab3.dto.SubjectList
import com.example.lab3.service.SubjectService
import kotlinx.coroutines.launch

class CatalogActivity : AppCompatActivity() {

    private val subjectService: SubjectService = SubjectService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        val subjectList: RecyclerView = findViewById(R.id.subjectList)
        val items = arrayListOf<SubjectDTO>()

        subjectList.layoutManager = LinearLayoutManager(this)
        val context = this

        lifecycleScope.launch {
            val subjects: SubjectList = subjectService.getAllSubjects()
            Log.d("CatalogActivity", subjects.toString())
            items.addAll(subjects.subjects)
            subjectList.adapter = SubjectAdapter(items, context)
        }
    }
}