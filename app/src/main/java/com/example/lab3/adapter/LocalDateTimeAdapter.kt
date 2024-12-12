package com.example.lab3.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class LocalDateTimeAdapter : TypeAdapter<LocalDateTime?>() {

    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: LocalDateTime?) {
        out.value(value?.format(formatter))
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): LocalDateTime? {
        val timestamp: String = `in`.nextString()
        return LocalDateTime.parse(timestamp, formatter)
    }
}