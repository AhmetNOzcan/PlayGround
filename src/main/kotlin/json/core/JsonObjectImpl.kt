package json.core

internal class JsonObjectImpl: JsonObjectInternal {
    private val items = mutableMapOf<String, JsonElement>()
    private var currentKey: String = ""

    override fun beginAdd(key: String) {
        if (currentKey.isNotEmpty()) {
            throw Exception("Unfinished value field!")
        }
        currentKey = key
    }

    override fun endAdd(value: JsonElement) {
        items[currentKey] = value
        currentKey = ""
    }

    override fun getString(key: String): String {
        return items[key]?.let {
            (it as? JsonStringValue)?.value
        } ?: throw Exception("")
    }

    override fun getInt(key: String): Int {
        return items[key]?.let {
            (it as? JsonIntegerValue)?.value
        } ?: throw Exception("")
    }

    override fun getBoolean(key: String): Boolean {
        return items[key]?.let {
            (it as? JsonBooleanValue)?.value
        } ?: throw Exception("")
    }

    override fun getDouble(key: String): Double {
        return items[key]?.let {
            (it as? JsonDoubleValue)?.value
        } ?: throw Exception("")
    }
}