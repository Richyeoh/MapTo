package com.copite.kotlin.m2.convert.defaults

import com.copite.kotlin.m2.convert.Converter
import com.google.gson.Gson

class MapToStringConverter : Converter {
    private val gson = Gson()

    override fun convert(value: Any?): Any? {
        if (value == null) return null
        return gson.toJson(value as Map<*, *>)
    }
}
