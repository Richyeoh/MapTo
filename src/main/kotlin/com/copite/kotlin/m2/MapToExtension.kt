package com.copite.kotlin.m2

import com.copite.kotlin.m2.convert.DefaultConverters
import java.lang.reflect.Field

inline fun <reified T : Any, reified R : Any> T.mapTo(targetClazz: Class<R>): R {
    val source = this
    val sourceClazz = this::class.java
    val target = targetClazz.newInstance()
    val sourceFields = sourceClazz.declaredFields
    for (sourceField in sourceFields) {
        try {
            val targetField = targetClazz.getDeclaredField(sourceField.name)
            val sourceValue = sourceField.getValue(source)
            val sourceType = sourceField.type
            val targetType = targetField.type
            val pair = Pair(sourceType, targetType)
            if (isSameType(sourceType, targetType)) {
                targetField.setValue(target, sourceValue)
            } else {
                val converter = DefaultConverters[pair]
                if (converter != null) {
                    targetField.setValue(target, converter.convert(targetField, sourceField, sourceValue))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return target
}

fun isSameType(type1: Class<*>, type2: Class<*>): Boolean {
    return type1 == type2
}

fun Field.getValue(clazz: Any): Any? {
    isAccessible = true
    return get(clazz)
}

fun Field.setValue(clazz: Any, value: Any?) {
    isAccessible = true
    return set(clazz, value)
}
