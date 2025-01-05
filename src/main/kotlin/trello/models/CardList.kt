package trello.models

import java.util.UUID

data class CardList(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val cardHolder: MutableList<Card> = mutableListOf()
)
