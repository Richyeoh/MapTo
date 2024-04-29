package com.copite.kotlin.m2.convert.defaults

import com.copite.kotlin.m2.convert.Converter

class FloatToStringConverter : Converter {
    override fun convert(value: Any?): Any? {
        return (value as Float).toString()
    }
}
