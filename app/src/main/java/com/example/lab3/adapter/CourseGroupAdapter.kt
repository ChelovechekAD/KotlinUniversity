package com.example.lab3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.dto.CourseGroupDTO
import com.example.lab3.enums.DayOfWeekRu

class CourseGroupAdapter(private val courseGroupList: List<CourseGroupDTO>, var context: Context) :
    RecyclerView.Adapter<CourseGroupAdapter.CourseGroupAdapterHolder>() {

    inner class CourseGroupAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val startTime: TextView = itemView.findViewById(R.id.groupStartTime)
        private val endTime: TextView = itemView.findViewById(R.id.groupEndTime)
        private val daysOfWeek: TextView = itemView.findViewById(R.id.groupDays)
        private val btn: Button = itemView.findViewById(R.id.groupBtn)

        fun bind(group: CourseGroupDTO) {
            startTime.text = "Время начала: %s".format(group.startTime.toString())
            endTime.text = "Время окончания: %s".format(group.endTime.toString())
            var days = "Дни недели: "
            for (elem in group.daysOfWeek) {
                days += "%s ".format(DayOfWeekRu.fromEnglishName(elem.name))
            }
            daysOfWeek.text = days
            btn.setOnClickListener {
                Toast.makeText(context, "Мы тебя запомнили!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseGroupAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_group_list, parent, false)
        return CourseGroupAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: CourseGroupAdapterHolder, position: Int) {
        holder.bind(courseGroupList[position])
    }

    override fun getItemCount(): Int {
        return courseGroupList.count()
    }
}