package json.core

internal class JsonArrayImpl: JsonArrayInternal {
    override val items: MutableList<JsonElement> = mutableListOf()
    override fun getAt(index: Int): JsonElement {
        TODO("Not yet implemented")
    }

    override fun size(): Int {
        TODO("Not yet implemented")
    }
}