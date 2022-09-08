package json

sealed interface JsonToken
object BeginObjectToken: JsonToken
object EndObjectToken: JsonToken
object BeginArrayToken: JsonToken
object EndArrayToken: JsonToken
object SemiColon: JsonToken
object CommaToken: JsonToken
object NullToken: JsonToken
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
    private val escapeCharacters = arrayOf('\\', '\n', ' ', '\t')
    private var previousToken: JsonToken? = null
    override fun analyze(json: String): List<JsonToken> {
        globalIndex = 0
        tokens.clear()

        while (globalIndex < json.length) {
            when (val c = json[globalIndex]) {
                '{' -> {
                    addToken(BeginObjectToken)
                }
                '"' -> {
                    readString(json)
                }
                '}' -> {
                    addToken(EndObjectToken)
                }
                '[' -> {
                    addToken(BeginArrayToken)
                }
                ']' -> {
                    addToken(EndArrayToken)
                }
                ':' -> {
                    addToken(SemiColon)
                }
                ',' -> {
                    addToken(CommaToken)
                }
                in '0'..'9' -> {
                    readNumber(json)
                }
                '-' -> {
                    readNumber(json)
                }
                in escapeCharacters -> {}
                else -> {
                    if (c.lowercaseChar() == 't' || c.lowercaseChar() == 'f') {
                        readBoolean(json)
                    }
                    else if (c.lowercaseChar() == 'n') {
                        readNull(json)
                    }
                    else {
                        throw Exception("Invalid character at $globalIndex")
                    }
                }
            }

            globalIndex++
        }

        if (tokens.first() is BeginObjectToken && tokens.last() !is EndObjectToken) {
            throw Exception("Invalid end of file")
        }
        else if (tokens.first() is BeginArrayToken && tokens.last() !is EndArrayToken) {
            throw Exception("Invalid end of file")
        }

        return tokens.toList()
    }

    private fun addToken(token: JsonToken) {
        previousToken?.let {
            when (it) {
                BeginArrayToken -> {
                    if (token is SemiColon || token is CommaToken || token is EndObjectToken) {
                        throw Exception("Invalid token $token")
                    }
                }
                BeginObjectToken -> {
                    if (token !is StringToken) {
                         if (token !is EndObjectToken){
                             throw Exception("Invalid token $token")
                        }
                    }
                }
                CommaToken -> {
                    if (token is BeginArrayToken ||
                            token is EndArrayToken ||
                            token is EndObjectToken ||
                            token is SemiColon) {
                        throw Exception("Invalid token $token")
                    }
                }
                EndArrayToken -> {
                    if(token !is CommaToken) {
                        if (token !is EndObjectToken) {
                            throw Exception("Invalid token $token")
                        }
                    }
                }
                EndObjectToken -> {
                    if (token !is CommaToken) {
                        if (token !is  EndObjectToken) {
                            if (token !is EndArrayToken) {
                                throw Exception("Invalid token $token")
                            }
                        }
                    }
                }
                SemiColon -> {
                    if(token is CommaToken ||
                            token is EndArrayToken||
                            token is EndObjectToken) {
                        throw Exception("Invalid token $token")
                    }
                }
                is DoubleToken, is BooleanToken, is IntegerToken, is NullToken -> {
                    if (token is BeginObjectToken ||
                            token is StringToken) {
                        throw Exception("Invalid token $token")
                    }
                }
                is StringToken -> {
                    if (token is DoubleToken ||
                            token is NullToken ||
                            token is IntegerToken ||
                            token is BooleanToken) {
                        throw Exception("Invalid token $token")
                    }
                }
            }
        }
        previousToken = token
        tokens.add(token)
    }

    private fun readNull(json: String) {
        val nullValue = json.substring(globalIndex, globalIndex + 4).lowercase()
        if (nullValue == "null") {
            globalIndex += nullValue.length -1
            addToken(NullToken)
        }
    }
    private fun readBoolean(json: String) {
        try {
            val boolTrue = json.substring(globalIndex, globalIndex + 4).lowercase()
            val boolFalse = json.substring(globalIndex, globalIndex + 5).lowercase()

            globalIndex += if ( boolTrue == "true") {
                addToken(BooleanToken(true))
                boolTrue.length - 1
            } else if (boolFalse == "false") {
                addToken(BooleanToken(false))
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
            addToken(DoubleToken(number.toDouble()))
        }
        else {
            addToken(IntegerToken(number.toInt()))
        }
    }

    private fun readString(json: String) {
        val nextQuoteIndex = json.indexOf('"', globalIndex + 1)
        val value = json.substring(globalIndex + 1, nextQuoteIndex)
        addToken(StringToken(value))
        globalIndex += value.length + 1
    }
}