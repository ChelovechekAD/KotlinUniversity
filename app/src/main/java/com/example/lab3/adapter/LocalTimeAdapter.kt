package com.example.lab3.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class LocalTimeAdapter : TypeAdapter<LocalTime?>() {

    private val formatter = DateTimeFormatter.ISO_TIME

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: LocalTime?) {
        out.value(value?.format(formatter))
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): LocalTime? {
        val timestamp: String = `in`.nextString()
        return LocalTime.parse(timestamp, formatter)
    }
}