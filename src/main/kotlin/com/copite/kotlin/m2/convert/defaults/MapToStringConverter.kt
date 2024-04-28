package com.copite.kotlin.m2.convert.defaults

import com.copite.kotlin.m2.convert.Converter
import com.google.gson.Gson
import java.lang.reflect.Field

class MapToStringConverter : Converter {
    private val gson = Gson()

    override fun convert(targetField: Field, sourceField: Field, sourceValue: Any?): Any? {
        return gson.toJson(sourceValue as Map<*, *>)
    }
}
