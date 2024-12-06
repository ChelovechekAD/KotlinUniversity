package com.example.lab3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.dto.SubjectDTO

class SubjectAdapter(private val items: List<SubjectDTO>, var context: Context) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.subjectTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subject_list, parent, false)
        return SubjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.title.text = items[position].subjectName;
    }

}