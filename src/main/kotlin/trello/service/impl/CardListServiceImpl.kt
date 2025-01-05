package trello.service.impl

import trello.models.Card
import trello.models.CardList
import trello.service.CardListService
import trello.service.CardService

class CardListServiceImpl: CardListService {
    private val cardService: CardService = CardServiceImpl()

    private val cardListRepository = mutableListOf<CardList>()

    override fun createCardList(name: String): CardList {

        val newCardList = CardList(
            name = name
        )
        cardListRepository.add(newCardList)

        return newCardList
    }

    override fun getCardList(id: String): CardList? {
        return cardListRepository.firstOrNull { it.id == id }
    }

    override fun deleteCardList(id: String): Boolean {
        val cardList = getCardList(id) ?: throw Exception("CARD LIST DON'T EXIST")

        if (cardList.cardHolder != null ) {
            deleteAllCards(cardList.cardHolder)
        }

        cardListRepository.removeIf { it.id == id }

        return true
    }

    override fun printCardList(cardListId: String?) {
        if (cardListId != null) {
            val cardList = getCardList(cardListId) ?: throw Exception("INVALID CARD LIST")
            println(cardList)
        } else {
            if (cardListRepository.size == 0) println("No CARD LIST")
            else {
                cardListRepository.map {
                    println(it)
                }
            }
        }
    }

    override fun addCard(cardListId: String, card: Card): Boolean {
        return try {
            val cardList = getCardList(cardListId) ?: throw Exception("INVALID CARD LIST")

            cardList.cardHolder.add(card)
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }

    override fun removeCard(cardListId: String, card: Card): Boolean {
        return try {
            val cardList = getCardList(cardListId) ?: throw Exception("INVALID CARD LIST")

            cardList.cardHolder.removeIf { it.id == card.id }
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }

    private fun deleteAllCards(listOfCards: MutableList<Card>) {
        listOfCards.map {
            cardService.deleteCard(
                it.id
            )
        }
    }
}