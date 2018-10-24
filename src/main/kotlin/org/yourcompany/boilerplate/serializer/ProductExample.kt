package org.yourcompany.boilerplate.serializer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON

@Serializable
data class ProductExample(val id: String, val name: String, val price: Int, val description: String)

fun ProductExample.toJson(): String {
    return JSON.stringify(this)
}

fun String.toProductExample(): ProductExample {
    return JSON.parse(this)
}
