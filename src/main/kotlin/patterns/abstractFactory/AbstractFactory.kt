package patterns.abstractFactory

interface Property {
    val name: String
    val value: Any
}

data class IntProperty(
    override val name: String,
    override val value: Int
): Property

data class StringProperty(
    override val name: String,
    override val value: Any
): Property


interface ServerConfiguration {
    val properties: List<Property>
}

data class ServerConfigurationImpl(
    override val properties: List<Property>
): ServerConfiguration

interface Parser {
    fun property(prop: String): Property
    fun server(propertyStrings: List<String>): ServerConfiguration
}

class JsonParser: Parser {
    override fun property(prop: String): Property {
        val (name, value) = prop.split(":")
        return when (name) {
            "port" -> IntProperty(name, value.trim().toInt())
            "environment" -> StringProperty(name, value.trim())
            else -> throw RuntimeException("Unknown property:   $name")
        }
    }

    override fun server(propertyStrings: List<String>): ServerConfiguration {
        val parsedProperties = mutableListOf<Property>()
        for (p in propertyStrings) {
            parsedProperties += property(p)
        }
        return ServerConfigurationImpl(parsedProperties)
    }
}
