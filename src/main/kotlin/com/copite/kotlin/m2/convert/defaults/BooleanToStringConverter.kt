package com.copite.kotlin.m2.convert.defaults

import com.copite.kotlin.m2.convert.Converter

class BooleanToStringConverter : Converter {
    override fun convert(value: Any?): Any? {
        return (value as Boolean).toString()
    }
}
