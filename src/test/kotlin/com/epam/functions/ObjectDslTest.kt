@file:Suppress("TestFunctionName")

package com.epam.functions


import com.epam.functions.data.*
import com.epam.functions.data.parseThrowing
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import com.google.gson.JsonObject as GsonObject

private typealias TestData = Pair<String, Iterable<TestItem>>

@RunWith(value = Parameterized::class)
internal class ObjectDslTest(
    private val testData: TestData
) {
    @Test
    fun `create object via DSL, give test dataset, give resulting json with expected valid data`() {
        val (testName, data) = testData

        withClue("Failed test: \"$testName\"") {
            parseThrowing<GsonObject>(JsonObject(data)) shouldBe GsonObject(data)
        }
    }

    private companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testDataset() = listOf(
            "test null item" to testNullItem(),
            "test string items" to testStringItems(),
            "test number items" to testNumberItems(),
            "test float items" to testFloatData(),
            "test double items" to testDoubleData(),
            "test boolean items" to testBooleanItems(),
            "test array item" to testArrayData()
        ).onEach { (_, data) -> checkForDuplicates(data) }
    }
}

private fun checkForDuplicates(
    elements: Collection<TestItem>
) =
    check(elements.groupBy(TestItem::key).values.all(Collection<*>::isSingleton)) {
        "Found duplicates: ${elements.groupBy(TestItem::key).filter { (_, l) -> l.size > 1 }}"
    }

private inline val Collection<*>.isSingleton get() = size == 1