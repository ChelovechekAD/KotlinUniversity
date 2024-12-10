package com.example.lab3.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.adapter.CourseGroupAdapter
import com.example.lab3.dto.CourseGroupDTO
import com.example.lab3.dto.CourseGroupList
import com.example.lab3.service.CourseGroupService
import kotlinx.coroutines.launch

class CourseActivity : AppCompatActivity() {

    private val courseGroupService: CourseGroupService = CourseGroupService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        val courseGroupRecycleView: RecyclerView = findViewById(R.id.courseGroupsRecycleView)
        val titleView: TextView = findViewById(R.id.courseInfoTitle)
        val items = arrayListOf<CourseGroupDTO>()

        courseGroupRecycleView.layoutManager = LinearLayoutManager(this)
        val context = this

        lifecycleScope.launch {
            val courseGroups: CourseGroupList = courseGroupService.getAllCourseGroupsByCourseId(
                intent.getLongExtra("courseId", 0)
            )
            val title = intent.getStringExtra("title")
            titleView.text = title
            Log.d("CourseActivity", courseGroups.toString())
            items.addAll(courseGroups.courseGroups)
            courseGroupRecycleView.adapter = CourseGroupAdapter(items, context)
        }
    }
}