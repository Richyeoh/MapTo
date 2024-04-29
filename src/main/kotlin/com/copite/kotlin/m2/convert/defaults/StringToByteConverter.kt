package com.copite.kotlin.m2.convert.defaults

import com.copite.kotlin.m2.convert.Converter

class StringToByteConverter : Converter {
    override fun convert(value: Any?): Any? {
        return (value as String).toByteOrNull()
    }
}
