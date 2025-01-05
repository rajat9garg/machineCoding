package trello.models

import java.util.UUID

enum class Privacy {
    PRIVATE,
    PUBLIC
}

data class Board(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val privacy: Privacy = Privacy.PUBLIC,
    val members: MutableList<User> = mutableListOf(),
    val listHolder: MutableList<CardList> = mutableListOf(),
) {
    val url: String = "$id.com"
}

