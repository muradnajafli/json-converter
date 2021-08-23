@file:Suppress("RemoveExplicitTypeArguments", "TestFunctionName")

package com.epam.functions


import com.epam.functions.data.parseThrowing
import com.epam.functions.data.testArrayData
import io.kotlintest.matchers.collections.shouldContainExactly
import io.kotlintest.matchers.maps.shouldContainExactly
import io.kotlintest.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.gson.JsonArray as GsonArray

@RunWith(JUnit4::class)
internal class DslTest {
    @Test
    fun `create object via DSL, give empty object DSL, then produce empty JSON`() =
        parseThrowing<Map<*, *>>(obj { }) shouldContainExactly emptyMap()

    @Test
    fun `create object via DSL, give non empty, then produce valid json array`() =
        testArrayData()
            .forEach { data -> parseThrowing<GsonArray>(data.jsonArray) shouldBe data.gsonElement }

    @Test
    fun `create object via DSL, give empty iterable, then produce empty JSON array`() =
        parseThrowing<List<*>>(arr[emptyList()]) shouldContainExactly emptyList()
}