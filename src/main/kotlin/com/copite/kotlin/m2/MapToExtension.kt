package com.copite.kotlin.m2

import com.copite.kotlin.m2.convert.DefaultConverters
import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Any, reified R : Any> T.mapTo(targetClazz: KClass<R>): R {
    val source = this
    val sourceClazz = this::class
    val sourceProperties = sourceClazz.declaredMemberProperties

    val targetConstruct = (targetClazz.primaryConstructor ?: targetClazz.constructors.first())
    val targetParameters = targetConstruct.parameters
    val primaryMap = mutableMapOf<KParameter, Any?>()

    for (targetParameter in targetParameters) {
        val sourceProperty = sourceProperties.find { it.name == targetParameter.name }
        if (sourceProperty != null) {
            val value = sourceProperty.call(source)
            // FIXME: If use type or returnType, Exist Kotlin type is nullable
            if (targetParameter.type.classifier == sourceProperty.returnType.classifier) {
                if (value != null) {
                    primaryMap[targetParameter] = value
                } else {
                    if (!targetParameter.isOptional && targetParameter.type.isMarkedNullable) {
                        primaryMap[targetParameter] = null
                    }
                }
            } else {
                // 1. Handle same name but different types (Use converter
                val sourceJClazz = (sourceProperty.returnType.classifier as KClass<*>).java
                val targetJClazz = (targetParameter.type.classifier as KClass<*>).java
                val pair = Pair(sourceJClazz, targetJClazz)
                val converter = DefaultConverters[pair]
                primaryMap[targetParameter] = converter?.convert(value)
            }
        } else {
            // 1. Parameter is marked nullable
            // 2. Parameter is optional
            // 3. If above is all false, Throw must parameter miss error
            if (targetParameter.type.isMarkedNullable && !targetParameter.isOptional) {
                primaryMap[targetParameter] = null
            }
        }
    }

    return targetConstruct.callBy(primaryMap)
}

fun isSameType(type1: KType, type2: KType): Boolean {
    return type1 == type2
}

fun KParameter.showParameter() {
    println("KParameter(name:$name, type:$type, kind:$kind, index:$index, isVararg:$isVararg, isOptional:$isOptional, isMarkedNullable:${type.isMarkedNullable}, classifier:${type.classifier})")
}

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
                    targetField.setValue(target, converter.convert(sourceValue))
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

fun Field.showField() {
    println("Field(name:$name, type:$type)")
}
