package com.example.lab3.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.dto.SubjectDTO
import com.example.lab3.service.CourseService
import kotlinx.coroutines.launch

class SubjectAdapter(
    private val items: List<SubjectDTO>,
    var context: Context,
) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    private val courseService = CourseService(context)

    inner class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.subjectTitle)
        private val courseRecyclerView: RecyclerView = view.findViewById(R.id.courseList)

        fun bind(subject: SubjectDTO) {
            title.text = subject.subjectName
            courseRecyclerView.layoutManager = LinearLayoutManager(context)

            itemView.setOnClickListener {
                subject.isExpanded = !subject.isExpanded
                (context as LifecycleOwner).lifecycleScope.launch {
                    if (subject.isExpanded) {
                        if (subject.courseList.isEmpty()) {
                            subject.courseList = courseService.getAllCoursesBySubjectId(subject.id)
                                .courses
                        }
                        Log.d("SubjectAdapter", subject.courseList.toString())
                        courseRecyclerView.adapter = CourseAdapter(subject.courseList, context)
                    } else {
                        courseRecyclerView.adapter = CourseAdapter(listOf(), context)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subject_list, parent, false)
        return SubjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(items[position])
    }

}