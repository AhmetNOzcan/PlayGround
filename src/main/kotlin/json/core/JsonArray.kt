package json.core

interface JsonArray: JsonElement {
    operator fun get(index: Int): JsonElement
    fun size(): Int
}