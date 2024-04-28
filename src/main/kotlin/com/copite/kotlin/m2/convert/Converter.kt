package com.copite.kotlin.m2.convert

import java.lang.reflect.Field

interface Converter {
    fun convert(targetField: Field, sourceField: Field, sourceValue: Any?): Any?
}
