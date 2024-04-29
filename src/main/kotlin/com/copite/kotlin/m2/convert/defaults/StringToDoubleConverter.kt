package com.copite.kotlin.m2.convert.defaults

import com.copite.kotlin.m2.convert.Converter

class StringToDoubleConverter : Converter {
    override fun convert(value: Any?): Any? {
        return (value as String).toDoubleOrNull()
    }
}
