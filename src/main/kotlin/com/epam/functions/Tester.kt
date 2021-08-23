package com.epam.functions

import java.util.*

fun main() {

    val student = obj {
        "name" relatedToValue "Alex"
        "age" relatedToValue 19
        "email" relatedToValue null
        "skills" relatedToValue obj {
            "hard" relatedToValue arr["Kotlin", "Android"]
            "soft" relatedToValue arr[listOf("Customer communication", "Team player")]
        }
        "token" relatedToValue UUID.randomUUID()
        "role" relatedToValue "user"
    }

    println("Student json:\n${student.toJsonString()}")
}