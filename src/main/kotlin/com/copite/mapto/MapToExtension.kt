package com.copite.mapto

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Any, reified R : Any> T.mapTo(): R {
    val values = T::class.declaredMemberProperties
        .associate { props ->
            Pair(props.name, props.get(this))
        }
    val primary = R::class.primaryConstructor
    val instance = if (primary != null) {
        val params = primary.parameters
            .associateWith {
                val name = it.name
                values[name]
            }
        primary.callBy(params)
    } else {
        R::class.createInstance()
    }
    return instance
}
