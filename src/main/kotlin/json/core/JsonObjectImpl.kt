package json.core

import json.BeginObjectToken
import json.JsonLexAnalyzerImpl
import json.JsonParser

internal class JsonObjectImpl(): JsonObjectInternal {
    private val items = mutableMapOf<String, JsonElement>()
    private var currentKey: String = ""

    constructor(json: String): this() {
        parse(json)
    }

    override fun beginAdd(key: String) {
        if (currentKey.isNotEmpty()) {
            throw Exception("Unfinished value field!")
        }
        currentKey = key
    }

    override fun endAdd(value: JsonElement) {
        if (currentKey.isEmpty()) {
            throw Exception("No key defined!")
        }
        items[currentKey] = value
        currentKey = ""
    }

    override fun get(key: String): JsonElement? {
        return items[key]
    }

    private fun parse(json: String) {
        val lexAnalyzer = JsonLexAnalyzerImpl()
        val lexResult = lexAnalyzer.analyze(json)

        if (lexResult.first() is BeginObjectToken){
            val parser = JsonParser()
            parser.parse(lexResult, this)
        }
        else {
            throw Exception("Excepted BeginObject!")
        }
    }
}