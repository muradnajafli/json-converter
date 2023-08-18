package com.epam.functions

@Suppress("ClassName")
object arr {
    /**
     * Creates json array from given [elements]. Each element
     * in the resulting json array must be converted applying the following rules:
     * - Any json primitive (number, string, etc.) must be used as is. In some cases numeric values
     * can't be represented without any transformations, in such cases string representation should
     * be used
     * - If element is instance of [JsonValue], it must be used as is
     * - Else convert element using [Any]'s [toString] method
     * For these you should use [toJsonValue] extension of [Any]
     */
    operator fun get(vararg elements: Any?): JsonArray {
        val jsonValues = elements.map { it?.toJsonValue() ?: JsonNull }
        return JsonArray.of(jsonValues)
    }
    /**
     * Creates json array from given [elements]. Each element
     * in the resulting json array must be converted applying the following rules:
     * - Any json primitive (number, string, etc.) must be used as is. In some cases numeric values
     * can't be represented without any transformations, in such cases string representation should
     * be used
     * - If element is instance of [JsonValue], it must be used as is
     * - Else convert element using [Any]'s [toString] method
     * For these you should use [toJsonValue] extension of [Any]
     */
    operator fun get(elements: Iterable<Any?>): JsonArray {
        val jsonValues = elements.map { it?.toJsonValue() ?: JsonNull }
        return JsonArray.of(jsonValues)
    }
}

/**
 * Creates json object using supplied configuration [block]
 */
fun obj(block: JsonObjectCreator.() -> Unit): JsonObject {
    val jsonObject = JsonObjectCreator()
    jsonObject.block()
    return jsonObject.toJsonTree()
}
