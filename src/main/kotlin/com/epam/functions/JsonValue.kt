package com.epam.functions

sealed class JsonValue {
    abstract fun toJsonString(): String
}

object JsonNull : JsonValue() {
    override fun toJsonString() = STRING_NULL
}

class JsonPrimitive private constructor(
    private val jsonValue: String
) : JsonValue() {

    companion object {
        private val TRUE = JsonPrimitive(true.toString())
        private val FALSE = JsonPrimitive(false.toString())

        fun of(
            value: Number
        ) = JsonPrimitive(value.toString())

        fun of(
            value: String
        ) = JsonPrimitive("\"$value\"")

        fun of(
            value: Boolean
        ) = if (value) TRUE else FALSE
    }

    override fun toJsonString(): String = jsonValue
}

class JsonArray private constructor(
    private val values: List<JsonValue>
) : JsonValue() {

    /**
     * Creates string from all elements, that `values` contains
     * Also you should use here `ARRAY_PREFIX`, `ARRAY_POSTFIX` constants
     * Each value should be transformed to string using their own `toJsonString' method
     */
    override fun toJsonString(): String {
        val elements = values.joinToString(", ") { it.toJsonString() }
        return "$ARRAY_PREFIX$elements$ARRAY_POSTFIX"

    }

    companion object {
        private val EMPTY = JsonArray(emptyList())

        fun of(
            values: List<JsonValue>
        ) = if (values.isEmpty()) EMPTY else JsonArray(values)
    }
}

class JsonObject private constructor(
    private val properties: Map<String, JsonValue>
) : JsonValue() {

    /**
     * Creates string from all elements, that `properties` contains
     * Also you should use here `OBJECT_PREFIX`, `OBJECT_POSTFIX` constants
     * and `toJsonProperty` extension
     */
    override fun toJsonString(): String  {
        val jsonProperties = properties.entries.joinToString(", ") { it.toJsonProperty() }
        return "$OBJECT_PREFIX$jsonProperties$OBJECT_POSTFIX"
    }

    companion object {
        private val EMPTY_OBJECT = JsonObject(emptyMap())

        fun of(
            properties: Map<String, JsonValue>
        ) = if (properties.isEmpty()) EMPTY_OBJECT else JsonObject(properties)
    }
}