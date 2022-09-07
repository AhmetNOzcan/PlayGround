package json.core

interface JsonArray: JsonElement {
    fun getAt(index: Int): JsonElement
    fun size(): Int
}