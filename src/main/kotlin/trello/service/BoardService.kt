package trello.service

import trello.models.Board
import trello.models.CardList
import trello.models.User

interface BoardService {

    fun createBoard(name: String): Board

    fun getBoard(boardId: String): Board?

    fun addMember(user: User, boardId: String): Boolean

    fun removeMember(user: User, boardId: String): Boolean

    fun deleteBoard(boardId: String): Boolean

    fun printBoard(boardId: String?)

    fun addList(boardId: String, cardList: CardList): Boolean

    fun removeList(boardId: String, cardList: CardList): Boolean

}