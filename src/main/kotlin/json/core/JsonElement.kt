package json.core

interface JsonElement

fun JsonElement.asJsonArray(): JsonArray {
    if (this is JsonArray)
        return this
    throw Exception("Invalid object type")
}

fun JsonElement.asJsonObject(): JsonObject {
    if (this is JsonObject)
        return this
    throw Exception("Invalid object type")
}

fun JsonElement.asString(): String {
    if (this is JsonStringValue)
        return this.value
    throw Exception("Invalid object type")
}

fun JsonElement.asBoolean(): Boolean {
    if (this is JsonBooleanValue)
        return this.value
    throw Exception("Invalid object type")
}
fun JsonElement.asInteger(): Int {
    if (this is JsonIntegerValue)
        return this.value
    throw Exception("Invalid object type")
}