package com.example.lab3.adapter

import com.example.lab3.dto.CourseDTO
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CourseDTODeserializer : JsonDeserializer<CourseDTO?> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext):
            CourseDTO {
        val jsonObject = json.asJsonObject
        return CourseDTO(
            id = jsonObject["id"].asLong,
            title = jsonObject["title"].asString,
            description = jsonObject["description"].asString,
            imageUrl = jsonObject["imageUrl"]?.let {
                if (it.isJsonNull) ""
                else it.asString
            } ?: ""

        )
    }
}