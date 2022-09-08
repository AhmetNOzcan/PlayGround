package json.core

sealed interface JsonValue: JsonElement

interface JsonStringValue: JsonValue {
    var value: String
}

interface JsonIntegerValue: JsonValue {
    var value: Int
}

interface JsonDoubleValue: JsonValue {
    var value: Double
}

interface JsonBooleanValue: JsonValue {
    var value: Boolean
}

object JsonNullValue: JsonValue

class JsonStringValueImpl(override var value: String): JsonStringValue

class JsonIntegerValueImpl(override var value: Int) : JsonIntegerValue

class JsonDoubleValueImpl(override var value: Double) : JsonDoubleValue

class JsonBooleanValueImpl(override var value: Boolean) : JsonBooleanValue

object EmptyValue: JsonValue