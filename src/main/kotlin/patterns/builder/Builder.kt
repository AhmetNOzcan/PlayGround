package patterns.builder

/*
    First approach
 */
class MailBuilder {
    private var to: List<String> = listOf()
    private var cc: List<String> = listOf()
    private var title: String = ""
    private var message: String = ""
    private var important: Boolean = false

    class Mail internal constructor(
        val to: List<String>,
        val cc: List<String>?,
        val title: String?,
        val message: String?,
        val important: Boolean
    )

    fun build(): Mail {
        if (to.isEmpty()) {
            throw RuntimeException("To property is empty")
        }

        return Mail(to, cc, title, message, important)
    }
}
/*
    Second approach
 */
data class Mail_V2(
    val to: List<String>,
    private var _message: String? = null,
    private var _cc: List<String>? = null,
    private var _title: String? = null,
    private var _important: Boolean? = null
) {
    fun message(message: String) = apply {
        _message = message
    }

    fun cc(cc: List<String>) = apply {
        _cc = cc
    }

    fun title(title: String) = apply {
        _title = title
    }

    fun important(important: Boolean) = apply {
        _important = important
    }
}

/*
    Third Approach
 */

data class Mail_v3(
    val to: List<String>,
    val cc: List<String> = listOf(),
    val title: String = "",
    val message: String = "",
    val important: Boolean = false,
)