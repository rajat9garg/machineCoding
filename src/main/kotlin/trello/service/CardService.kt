package trello.service

import trello.models.Card
import trello.models.User

interface CardService {

    fun createCard(name: String, description: String): Card

    fun getCard(id: String): Card?

    fun assignUser(cardId: String, user: User): Card

    fun deleteCard(cardId: String): Boolean

}