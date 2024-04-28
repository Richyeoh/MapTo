package com.copite.kotlin.m2.convert.defaults

import com.copite.kotlin.m2.convert.Converter
import java.lang.reflect.Field

class DoubleToStringConverter : Converter {
    override fun convert(targetField: Field, sourceField: Field, sourceValue: Any?): Any? {
        return (sourceValue as Double).toString()
    }
}
