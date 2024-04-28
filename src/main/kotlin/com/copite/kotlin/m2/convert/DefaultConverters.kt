package com.copite.kotlin.m2.convert

import com.copite.kotlin.m2.convert.defaults.*

object DefaultConverters {
    private val converters = mapOf(
        Pair(Boolean::class.java, String::class.java) to BooleanToStringConverter(),
        Pair(String::class.java, Boolean::class.java) to StringToBooleanConverter(),
        Pair(Byte::class.java, String::class.java) to ByteToStringConverter(),
        Pair(String::class.java, Byte::class.java) to StringToByteConverter(),
        Pair(Short::class.java, String::class.java) to ShortToStringConverter(),
        Pair(String::class.java, Short::class.java) to StringToShortConverter(),
        Pair(Int::class.java, String::class.java) to IntegerToStringConverter(),
        Pair(String::class.java, Int::class.java) to StringToIntegerConverter(),
        Pair(Long::class.java, String::class.java) to LongToStringConverter(),
        Pair(String::class.java, Long::class.java) to StringToLongConverter(),
        Pair(Float::class.java, String::class.java) to FloatToStringConverter(),
        Pair(String::class.java, Float::class.java) to StringToFloatConverter(),
        Pair(Double::class.java, String::class.java) to DoubleToStringConverter(),
        Pair(String::class.java, Double::class.java) to StringToDoubleConverter(),
        Pair(Map::class.java, String::class.java) to MapToStringConverter(),
        Pair(String::class.java, Map::class.java) to StringToMapConverter(),
    )

    operator fun get(pair: Pair<Class<*>, Class<*>>): Converter? {
        return converters[pair]
    }
}
