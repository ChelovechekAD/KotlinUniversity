package com.example.lab3.adapter

import com.example.lab3.enums.ErrorCode
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class ErrorCodeAdapter : TypeAdapter<ErrorCode?>() {
    override fun write(out: JsonWriter, value: ErrorCode?) {
        out.value(value?.name)
    }

    override fun read(`in`: JsonReader): ErrorCode? {
        return try {
            ErrorCode.valueOf(`in`.nextString())
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}