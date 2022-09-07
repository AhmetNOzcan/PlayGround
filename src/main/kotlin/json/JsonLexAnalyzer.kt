package json

sealed interface JsonToken
object BeginObjectToken: JsonToken
object EndObjectToken: JsonToken
object BeginArrayToken: JsonToken
object EndArrayToken: JsonToken
object SemiColon: JsonToken
object CommaToken: JsonToken
data class StringToken(val value: String): JsonToken
data class IntegerToken(val value: Int): JsonToken
data class DoubleToken(val value: Double): JsonToken
data class BooleanToken(val value: Boolean): JsonToken

interface JsonLexAnalyzer {
    fun analyze(json: String): List<JsonToken>
}

class JsonLexAnalyzerImpl: JsonLexAnalyzer {
    var globalIndex = 0
    private val tokens = mutableListOf<JsonToken>()
    private val escapeCharacters = arrayOf('\\', '\n', ' ')

    override fun analyze(json: String): List<JsonToken> {
        globalIndex = 0
        tokens.clear()

        while (globalIndex < json.length) {
            when (json[globalIndex]) {
                '{' -> {
                    tokens.add(BeginObjectToken)
                }
                '"' -> {
                    readString(json)
                }
                '}' -> {
                    tokens.add(EndObjectToken)
                }
                '[' -> {
                    tokens.add(BeginArrayToken)
                }
                ']' -> {
                    tokens.add(EndArrayToken)
                }
                ':' -> {
                    tokens.add(SemiColon)
                }
                ',' -> {
                    tokens.add(CommaToken)
                }
                in '0'..'9' -> {
                    readNumber(json)
                }
                '-' -> {
                    readNumber(json)
                }
                in escapeCharacters -> {}
                else -> {
                    readBoolean(json)
                }
            }

            globalIndex++
        }

        return tokens.toList()
    }

    private fun readBoolean(json: String) {
        try {
            val boolTrue = json.substring(globalIndex, globalIndex + 4).lowercase()
            val boolFalse = json.substring(globalIndex, globalIndex + 5).lowercase()

            globalIndex += if ( boolTrue == "true") {
                tokens.add(BooleanToken(true))
                boolTrue.length - 1
            } else if (boolFalse == "false") {
                tokens.add(BooleanToken(false))
                boolFalse.length - 1
            } else {
                throw Exception("Invalid character at $globalIndex")
            }
        }
        catch (e: Exception) {
            throw Exception("Invalid character at $globalIndex")
        }
    }

    private fun readNumber(json: String) {
        var number =""
        while (json[globalIndex].isDigit() || json[globalIndex] == '.' || json[globalIndex] == '-') {
            number += json[globalIndex]
            globalIndex++
        }

        globalIndex--

        if (number.contains('.')) {
            tokens.add(DoubleToken(number.toDouble()))
        }
        else {
            tokens.add(IntegerToken(number.toInt()))
        }
    }

    private fun readString(json: String) {
        val nextQuoteIndex = json.indexOf('"', globalIndex + 1)
        val value = json.substring(globalIndex + 1, nextQuoteIndex)
        tokens.add(StringToken(value))
        globalIndex += value.length + 1
    }
}