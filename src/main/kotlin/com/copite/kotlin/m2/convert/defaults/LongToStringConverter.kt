package com.copite.kotlin.m2.convert.defaults

import com.copite.kotlin.m2.convert.Converter

class LongToStringConverter : Converter {
    override fun convert(value: Any?): Any? {
        return (value as Long).toString()
    }
}
