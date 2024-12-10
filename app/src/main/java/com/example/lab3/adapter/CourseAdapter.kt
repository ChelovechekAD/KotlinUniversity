package com.example.lab3.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab3.R
import com.example.lab3.activity.CourseActivity
import com.example.lab3.dto.CourseDTO
import kotlinx.coroutines.launch

class CourseAdapter(private val courseList: List<CourseDTO>, var context: Context) :
    RecyclerView.Adapter<CourseAdapter.CourseAdapterHolder>() {

    inner class CourseAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.courseTitle)
        private val imageView: ImageView = itemView.findViewById(R.id.courseImage)
        private val description: TextView = itemView.findViewById(R.id.courseDescription)
        private val btn: Button = itemView.findViewById(R.id.courseBtn)

        fun bind(course: CourseDTO) {
            textView.text = course.title
            Log.d("CourseAdapter", course.toString())
            if (course.imageUrl != "") {
                (context as LifecycleOwner).lifecycleScope.launch {
                    Glide.with(context)
                        .load(course.imageUrl)
                        .error(R.drawable.not_found)
                        .placeholder(R.drawable.not_found)
                        .into(imageView)
                }
            }
            description.text = course.description
            btn.setOnClickListener {
                val intent = Intent(context, CourseActivity::class.java)
                intent.flags = FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("courseId", course.id)
                intent.putExtra("title", course.title)
                startActivity(context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list, parent, false)
        return CourseAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: CourseAdapterHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int {
        return courseList.count()
    }
}