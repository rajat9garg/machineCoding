package trello.service

import trello.models.Card
import trello.models.CardList

interface CardListService {

    fun createCardList(name: String): CardList

    fun getCardList(id: String): CardList?

    fun deleteCardList(id: String): Boolean

    fun printCardList(cardListId: String?)

    fun addCard(cardListId: String, card: Card): Boolean

    fun removeCard(cardListId: String, card: Card): Boolean


}