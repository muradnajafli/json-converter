package com.epam.functions

// It's a common trick to expose object with set of operators to get a nicer DSL
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
     */
    operator fun get(
        vararg elements: Any?
    ) = TODO("Return json array")

    /**
     * Creates json array from given [elements]. Each element
     * in the resulting json array must be converted applying the following rules:
     * - Any json primitive (number, string, etc.) must be used as is. In some cases numeric values
     * can't be represented without any transformations, in such cases string representation should
     * be used
     * - If element is instance of [JsonValue], it must be used as is
     * - Else convert element using [Any]'s [toString] method
     */
    operator fun get(
        elements: Iterable<Any?>
    ) = TODO("Return json array")
}

/**
 * Creates json object using supplied configuration [block]
 */
fun obj(
    block: JsonObjectBuilder.() -> Unit
) = TODO("Return json object")

class JsonObjectBuilder {

    /**
     * Suitable for adding null literals
     */
    fun String.by(
        @Suppress("UNUSED_PARAMETER") value: Nothing?
    ): Unit = TODO()

    /**
     * Adds a string
     */
    fun String.by(
        value: String?
    ): Unit = TODO()

    /**
     * Adds a number
     */
    fun String.by(
        value: Number?
    ): Unit = TODO()

    /**
     * Adds a boolean
     */
    fun String.by(
        value: Boolean?
    ): Unit = TODO()

    /**
     * Adds a character
     */
    fun String.by(
        value: Char?
    ): Unit = TODO()

    /**
     * Adds a json value
     */
    fun String.by(
        value: JsonValue
    ): Unit = TODO()

    /**
     * Adds property by applying the following rules:
     * - Any json primitive (number, string, etc.) must be used as is. In some cases numeric values
     * can't be represented without any transformations, in such cases string representation should
     * be used
     * - If element is instance of [JsonValue], it must be used as is
     * - Else convert element using [Any]'s [toString] method
     */
    fun String.by(
        value: Any?
    ): Unit = TODO()
}

/**
 * Contract for all json values
 */
sealed class JsonValue {

    /**
     * Converts this json value to a string
     */
    abstract fun toJsonString(): String

    /**
     * Same as [toJsonString] but with pretty printing
     * @param level current json value level. For example,
     * ```json
     * {
     * "bool": true, <-- level == 0
     * "property":
     *          { <-- level == 1
     *          "nested_property": ...
     *          }
     * }
     * ```
     * @param indent number of tab literals `\t` to be used for indent.
     * By default it is 1
     */
    abstract fun toPrettyJsonString(
        level: Int = 0,
        indent: Int = 1
    ): String
}