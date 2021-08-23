package com.epam.functions


fun Map.Entry<String, JsonValue>.toJsonProperty(): String = "\"$key\": ${value.toJsonString()}"

fun Any?.toJsonValue(): JsonValue =
    when (this) {
        is Number -> JsonPrimitive.of(this)
        is String -> JsonPrimitive.of(this)
        is Boolean -> JsonPrimitive.of(this)
        is JsonValue -> this
        null -> JsonNull
        else -> JsonPrimitive.of(toString())
    }
