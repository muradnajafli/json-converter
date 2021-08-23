package com.epam.functions.data

import com.epam.functions.JsonValue
import com.google.gson.GsonBuilder

internal val TEST_GSON = GsonBuilder()
    .setPrettyPrinting()
    .create()

internal inline fun <reified T> parseThrowing(
    jsonString: String
): T =
    try {
        TEST_GSON.fromJson(jsonString, T::class.java)
    } catch (th: Throwable) {
        throw IllegalArgumentException("Malformed json=$jsonString. Check it for validity", th)
    }

internal inline fun <reified T> parseThrowing(
    value: JsonValue
): T = parseThrowing(value.toJsonString())