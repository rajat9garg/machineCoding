package trello.models

enum class Operations(val value: String) {
    SHOW("SHOW"),
    BOARD("BOARD"),
    LIST("LIST"),
    CARD("CARD");

    companion object {
        fun findValue(value: String): Operations? {
            return entries.find { it.value == value }
        }
    }
}