package com.copite.mapto

import kotlin.reflect.full.*

inline fun <reified T : Any, reified R : Any> T.mapTo(): R {
    val source = T::class
        .declaredMemberProperties
        .filter { prop ->
            prop.get(this) != null
        }
        .associate { prop ->
            Pair(prop.name, prop.get(this))
        }
    val primary = R::class.primaryConstructor
    val target = if (primary != null) {
        val params = primary.parameters
            .filter { param ->
                source.containsKey(param.name)
            }
            .associateWith { param ->
                source[param.name]
            }
        primary.callBy(params)
    } else {
        R::class.primaryConstructor!!.callBy(emptyMap())
    }
    return target
}
