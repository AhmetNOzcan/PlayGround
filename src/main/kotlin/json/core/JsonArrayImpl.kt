package json.core

import json.BeginArrayToken
import json.JsonLexAnalyzerImpl
import json.JsonParser

internal class JsonArrayImpl(): JsonArrayInternal {
    override val items: MutableList<JsonElement> = mutableListOf()

    constructor(json: String): this() {
        parse(json)
    }

    override fun get(index: Int): JsonElement {
        return items[index]
    }

    override fun size(): Int {
        return items.size
    }

    private fun parse(json: String) {
        val lexAnalyzer = JsonLexAnalyzerImpl()
        val lexResult = lexAnalyzer.analyze(json)

        if (lexResult.first() is BeginArrayToken){
            val parser = JsonParser()
            parser.parse(lexResult, this)
        }
        else {
            throw Exception("Excepted BeginArray!")
        }
    }
}