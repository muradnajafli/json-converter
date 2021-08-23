package com.epam.functions.data

import com.epam.functions.JsonArray
import com.epam.functions.JsonObjectCreator
import com.epam.functions.arr
import com.google.gson.JsonArray as GsonArray
import com.google.gson.JsonElement as GsonElement
import com.google.gson.JsonNull as GsonNull
import com.google.gson.JsonPrimitive as GsonPrimitive

/**
 * Utility class that allows configuring [com.epam.functions.JsonObjectCreator]
 */
internal sealed class TestItem {
    abstract val key: String
    abstract fun JsonObjectCreator.applyToBuilder()
    abstract val gsonElement: GsonElement
}

internal data class StringItem(
    override val key: String,
    val value: String?
) : TestItem() {
    override fun JsonObjectCreator.applyToBuilder() = key relatedToValue value
    override val gsonElement: GsonElement = value?.let(::GsonPrimitive) ?: GsonNull.INSTANCE
}

internal data class NullItem(
    override val key: String
) : TestItem() {
    override fun JsonObjectCreator.applyToBuilder() = key relatedToValue null
    override val gsonElement: GsonElement = GsonNull.INSTANCE
}

internal data class BooleanItem(
    override val key: String,
    val value: Boolean?
) : TestItem() {
    override fun JsonObjectCreator.applyToBuilder() = key relatedToValue value
    override val gsonElement: GsonElement = value?.let(::GsonPrimitive) ?: GsonNull.INSTANCE
}

internal data class NumberItem(
    override val key: String,
    val value: Number?
) : TestItem() {
    override fun JsonObjectCreator.applyToBuilder() = key relatedToValue value
    override val gsonElement: GsonElement =
        // We want NaN and both infinities to be represented as strings in a final json
        if (value?.shouldWrapAsDouble() == true || value?.shouldWrapAsFloat() == true) GsonPrimitive(value.toString())
        else value?.let(::GsonPrimitive) ?: GsonNull.INSTANCE
}

internal data class ArrayItem(
    override val key: String,
    val jsonArray: JsonArray,
    override val gsonElement: GsonArray
) : TestItem() {

    constructor(field: String, rawList: List<Any?>, gsonElement: GsonArray) : this(field, arr[rawList], gsonElement)
    override fun JsonObjectCreator.applyToBuilder() = key relatedToValue jsonArray
}

private fun Number.shouldWrapAsDouble(): Boolean =
    this is Double && (isNaN() || this == Double.POSITIVE_INFINITY || this == Double.NEGATIVE_INFINITY)

private fun Number.shouldWrapAsFloat(): Boolean =
    this is Float && (isNaN() || this == Float.POSITIVE_INFINITY || this == Float.NEGATIVE_INFINITY)
