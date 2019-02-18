package id.jasoet.boilerplate.serializer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import kotlinx.serialization.stringify

@Serializable
data class ProductExample(val id: String, val name: String, val price: Int, val description: String)

fun ProductExample.toJson(): String {
    return Json.stringify(this)
}

fun String.toProductExample(): ProductExample {
    return Json.parse(this)
}
