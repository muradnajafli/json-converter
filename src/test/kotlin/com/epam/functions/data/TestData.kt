@file:Suppress("TestFunctionName")

package com.epam.functions.data

import com.epam.functions.arr
import com.epam.functions.obj
import com.google.gson.JsonArray

internal fun testNullItem(): List<NullItem> =
    listOf(NullItem("null"))

internal fun testBooleanItems(): List<BooleanItem> = listOf(
    BooleanItem("true", true),
    BooleanItem("false", false),
    BooleanItem("null", null)
)

internal fun testStringItems(): List<TestItem> =
    listOf(
        StringItem("string", "string"),
        StringItem("empty_string", ""),
        StringItem("null", null),
    )

internal fun testNumberItems() = listOf(
    NumberItem("null", null),
    NumberItem("some_int", 19),
    NumberItem("max_int", Int.MAX_VALUE),
    NumberItem("min_int", Int.MIN_VALUE),
    NumberItem("negative_int", -21),
    NumberItem("some_long", 221L),
    NumberItem("max_long", Long.MAX_VALUE),
    NumberItem("min_long", Long.MIN_VALUE),
    NumberItem("negative_long", -192L),
)

internal fun testFloatData() = listOf(
    NumberItem("positive_float", 19f),
    NumberItem("negative_float", -92f)
)

internal fun testDoubleData() = listOf(
    NumberItem("positive_double", 61.0),
    NumberItem("negative_double", -31.0),
    NumberItem("max_double", Double.MAX_VALUE),
    NumberItem("min_double", Double.MIN_VALUE),
    NumberItem("nan_double", Double.NaN),
    NumberItem("positive_inf_double", Double.POSITIVE_INFINITY),
    NumberItem("negative_inf_double", Double.NEGATIVE_INFINITY)
)

@Suppress("CAST_NEVER_SUCCEEDS")
internal fun testArrayData(): List<ArrayItem> {
    val testAny = Any()
    val testStringItems = testStringItems()
    val testNull = null
    val testString = "string test"
    val testInt = 20
    val testDouble = 13.0
    val testFloat = 3f
    val testBooleanTrue = true
    val testBooleanFalse = false
    val emptyObj = obj {}
    val notEmptyObj = obj {
        "array_key" relatedToValue arr[testNull, testString, testAny]
        "obj_key" relatedToValue obj {}
    }
    val emptyArr = arr[emptyList()]
    val notEmptyArr =
        arr[testNull, testString, testInt, testDouble, testFloat, testBooleanFalse, testBooleanTrue, testAny]

    val rawValues = listOf(
        testNull,
        testString,
        testInt,
        testDouble,
        testFloat,
        testBooleanTrue,
        testBooleanFalse,
        testAny,
        emptyObj,
        notEmptyObj,
        JsonObject(testStringItems),
        emptyArr,
        notEmptyArr
    )

    val gsonValues = GsonArray {
        add(testNull as? String)
        add(testString)
        add(testInt)
        add(testDouble)
        add(testFloat)
        add(testBooleanTrue)
        add(testBooleanFalse)
        add(testAny.toString())
        add(GsonObject())
        add(GsonObject {
            add("array_key", GsonArray {
                add(testNull as? String)
                add(testString)
                add(testAny.toString())
            })
            add("obj_key", GsonObject())
        })
        add(GsonObject(testStringItems))
        add(JsonArray())
        add(GsonArray {
            add(testNull as? String)
            add(testString)
            add(testInt)
            add(testDouble)
            add(testFloat)
            add(testBooleanFalse)
            add(testBooleanTrue)
            add(testAny.toString())
        })
    }

    return listOf(
        ArrayItem("empty_array_field", listOf(), JsonArray()),
        ArrayItem("array_field", rawValues, gsonValues)
    )
}

internal fun JsonObject(
    elements: Iterable<TestItem>
) = obj { elements.forEach { element -> with(element) { applyToBuilder() } } }

internal fun GsonObject(
    elements: Iterable<TestItem>
) = com.google.gson.JsonObject()
    .apply { elements.forEach { element -> add(element.key, element.gsonElement) } }

internal fun GsonObject(
    block: com.google.gson.JsonObject.() -> Unit = {}
) = com.google.gson.JsonObject()
    .apply(block)

internal fun GsonArray(
    block: JsonArray.() -> Unit
) = JsonArray().apply(block)