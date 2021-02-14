package com.epam.functions

import java.time.LocalDateTime
import java.util.*

fun main() {

    val user = obj {
        "firstName" by "John"
        "lastName" by "Smith"
        "age" by 20
        "email" by null
        "interests" by arr["kotlin", "programming", "computer science"]
        "metadata" by obj {
            "token" by UUID.randomUUID()// 075a68b5-05f0-4e90-84d9-e418ab68fe3a
            "expires" by LocalDateTime.now().plusDays(7)// 2021-02-02T00:26:50.307
            "role" by "admin"
        }
    }

    println("User json:\n${user.toJsonString()}\nUser pretty json:\n${user.toPrettyJsonString()}")
}