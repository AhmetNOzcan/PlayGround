package json.core

import json.core.JsonElement

interface JsonObject: JsonElement {
    operator fun get(key: String): JsonElement?
}