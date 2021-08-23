package com.epam.functions

@JsonAnnotation
class JsonObjectCreator {

    private val properties = mutableMapOf<String, JsonValue>()

    infix fun String.relatedToValue(
        @Suppress("UNUSED_PARAMETER") value: Nothing?
    ) = append(JsonNull)

    infix fun String.relatedToValue(
        value: String?
    ) = append(value?.let(JsonPrimitive::of) ?: JsonNull)

    infix fun String.relatedToValue(
        value: Number?
    ) = append(value?.let(JsonPrimitive::of) ?: JsonNull)

    infix fun String.relatedToValue(
        value: Boolean?
    ) = append(value?.let(JsonPrimitive::of) ?: JsonNull)

    infix fun String.relatedToValue(value: JsonValue) = append(value)

    infix fun String.relatedToValue(value: Any?) = append(value.toJsonValue())

    fun toJsonTree(): JsonObject = JsonObject.of(properties)

    private fun String.append(value: JsonValue) {
        properties[this] = value
    }
}