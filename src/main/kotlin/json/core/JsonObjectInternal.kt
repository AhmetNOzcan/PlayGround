package json.core

import json.core.JsonElement
import json.core.JsonObject

internal interface JsonObjectInternal: JsonObject {
    fun beginAdd(key: String)
    fun endAdd(value: JsonElement)
}