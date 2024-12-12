package com.example.lab3.adapter

import com.example.lab3.dto.CourseDTO
import com.example.lab3.dto.SubjectDTO
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SubjectDTODeserializer : JsonDeserializer<SubjectDTO?> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext):
            SubjectDTO {
        val jsonObject = json.asJsonObject
        return SubjectDTO(
            id = jsonObject["id"].asLong,
            subjectName = jsonObject["subjectName"].asString,
            courseList = jsonObject["courseList"]?.let {
                context.deserialize(it, object : TypeToken<List<CourseDTO>>() {}.type)
            } ?: listOf(), // Если null, заменяем на пустой список
            isExpanded = jsonObject["isExpanded"]?.asBoolean ?: false
        )
    }
}