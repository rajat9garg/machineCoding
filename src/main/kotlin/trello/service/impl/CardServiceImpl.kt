package trello.service.impl

import trello.common.CardNotFoundException
import trello.models.Card
import trello.models.User
import trello.service.CardService

class CardServiceImpl: CardService {
    val cardRepository = mutableListOf<Card>()

    override fun createCard(name: String, description: String): Card {
        val newCard = Card(
            name = name,
            description = description
        )

        cardRepository.add(newCard)

        return newCard
    }

    override fun getCard(id: String): Card? {
        return cardRepository.firstOrNull { it.id == id }
    }

    override fun assignUser(cardId: String, user: User): Card {
        val card = getCard(cardId) ?: throw CardNotFoundException("Card with ID $cardId not found")
        card.assignedUser = user

        return card
    }

    override fun deleteCard(cardId: String): Boolean {
        return cardRepository.removeIf { it.id == cardId }
    }
}