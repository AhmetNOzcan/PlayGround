package json.core

internal interface JsonArrayInternal: JsonArray {
    val items: MutableList<JsonElement>
}