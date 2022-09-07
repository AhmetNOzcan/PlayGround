package patterns.staticFactory

class Server private constructor(port: Long) {
    companion object {
        fun withPort(port: Long) = Server(port)
    }
}

fun testStaticFactory() {
    val server =  Server.withPort(3)
}