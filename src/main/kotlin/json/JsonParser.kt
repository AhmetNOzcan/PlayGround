package json

import json.core.*
import json.core.JsonArrayImpl
import json.core.JsonArrayInternal
import json.core.JsonObjectImpl
import json.core.JsonObjectInternal

class JsonParser {
    var globalIndex = 0
    var depth = 0

    fun parse(list: List<JsonToken>, jsonElement: JsonElement? = null): JsonElement {
        val element = when (list.first()) {
                is BeginObjectToken -> {
                    if (jsonElement != null && jsonElement !is JsonObjectInternal){
                        throw Exception()
                    }
                    else if (jsonElement is JsonObjectInternal) {
                        jsonElement
                    }
                    else {
                        JsonObjectImpl()
                    }
                }
                is BeginArrayToken -> {
                    if (jsonElement != null && jsonElement !is JsonArrayInternal){
                        throw Exception()
                    }
                    else if (jsonElement is JsonArrayInternal) {
                        jsonElement
                    }
                    else {
                        JsonArrayImpl()
                    }
                }
                else -> {
                    null
                }
            }

        val result = processToken(list, element!!)
        if (depth != 0) {
            throw Exception("Unclosed json structure")
        }
        return result
    }

    private fun processToken(list: List<JsonToken>, jsonElement: JsonElement): JsonElement {
        depth++
        while (globalIndex < list.size - 1) {
            globalIndex++
            val prevJsonToken = list[globalIndex - 1]
            when (val currentToken = list[globalIndex]) {
                BeginObjectToken -> {
                    if (jsonElement is JsonArrayInternal) {
                        val obj = JsonObjectImpl()
                        jsonElement.items.add(obj)
                        processToken(list, obj)
                    }
                    else if (prevJsonToken is SemiColon && jsonElement is JsonObjectInternal) {
                        val obj = JsonObjectImpl()
                        jsonElement.endAdd(obj)
                        processToken(list, obj)
                    }
                    else {
                        throw Exception("Unrecognized syntax")
                    }
                }
                BeginArrayToken -> {
                    when (prevJsonToken) {
                        is SemiColon -> {
                            if (jsonElement is JsonObjectInternal) {
                                val array = JsonArrayImpl()
                                jsonElement.endAdd(array)
                                processToken(list, array)
                            }
                            else {
                                throw Exception("Unrecognized syntax")
                            }
                        }
                        else -> throw Exception("Unrecognized syntax")
                    }
                }
                EndObjectToken -> {
                    depth--
                    break
                }
                EndArrayToken -> {
                    depth--
                    break
                }
                CommaToken -> {
                    continue
                }
                SemiColon -> {
                    continue
                }
                is StringToken -> {
                    when(prevJsonToken) {
                        is SemiColon -> {
                            if (jsonElement is JsonObjectInternal) {
                                jsonElement.endAdd(JsonStringValueImpl(currentToken.value))
                            }
                            else {
                                throw Exception("Invalid json element")
                            }
                        }
                        is BeginObjectToken -> {
                            if (jsonElement is JsonObjectInternal) {
                                jsonElement.beginAdd(currentToken.value)
                            }
                            else {
                                throw Exception()
                            }
                        }
                        is BeginArrayToken -> {
                            if (jsonElement is JsonArrayInternal) {
                                jsonElement.items.add(JsonStringValueImpl(currentToken.value))
                            }
                            else {
                                throw Exception("Invalid json element")
                            }
                        }
                        is CommaToken -> {
                            if (jsonElement is JsonObjectInternal) {
                                jsonElement.beginAdd(currentToken.value)
                            }
                            else if (jsonElement is JsonArrayInternal) {
                                jsonElement.items.add(JsonStringValueImpl(currentToken.value))
                            }
                        }
                        else -> throw Exception()
                    }
                }
                is IntegerToken -> {
                    if (jsonElement is JsonObjectInternal && prevJsonToken is SemiColon) {
                        jsonElement.endAdd(JsonIntegerValueImpl(currentToken.value))
                    }
                    else if (jsonElement is JsonArrayInternal && (prevJsonToken is CommaToken || prevJsonToken is BeginArrayToken)) {
                        jsonElement.items.add(JsonIntegerValueImpl(currentToken.value))
                    }
                    else {
                        throw Exception("Unexpected object")
                    }
                }
                is DoubleToken -> {
                    if (jsonElement is JsonObjectInternal && prevJsonToken is SemiColon) {
                        jsonElement.endAdd(JsonDoubleValueImpl(currentToken.value))
                    }
                    else if (jsonElement is JsonArrayInternal && (prevJsonToken is CommaToken || prevJsonToken is BeginArrayToken)) {
                        jsonElement.items.add(JsonDoubleValueImpl(currentToken.value))
                    }
                    else {
                        throw Exception("Unexpected object")
                    }
                }
                is BooleanToken -> {
                    if (jsonElement is JsonObjectInternal && prevJsonToken is SemiColon) {
                        jsonElement.endAdd(JsonBooleanValueImpl(currentToken.value))
                    }
                    else if (jsonElement is JsonArrayInternal && (prevJsonToken is CommaToken || prevJsonToken is BeginArrayToken)) {
                        jsonElement.items.add(JsonBooleanValueImpl(currentToken.value))
                    }
                    else {
                        throw Exception("Unexpected object")
                    }
                }
                NullToken -> {
                    if (jsonElement is JsonObjectInternal && prevJsonToken is SemiColon) {
                        jsonElement.endAdd(JsonNullValue)
                    }
                    else if (jsonElement is JsonArrayInternal && (prevJsonToken is CommaToken || prevJsonToken is BeginArrayToken)) {
                        jsonElement.items.add(JsonNullValue)
                    }
                    else {
                        throw Exception("Unexpected object")
                    }
                }
            }
        }

        return jsonElement
    }
}