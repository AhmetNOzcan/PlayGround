package json.core

import json.core.JsonElement

interface JsonObject: JsonElement {
    fun getString(key: String): String
    fun getInt(key: String): Int
    fun getBoolean(key: String): Boolean
    fun getDouble(key: String): Double
}