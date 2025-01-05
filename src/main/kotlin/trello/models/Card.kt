package trello.models

import java.util.UUID

data class Card(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    var assignedUser: User?= null
)
