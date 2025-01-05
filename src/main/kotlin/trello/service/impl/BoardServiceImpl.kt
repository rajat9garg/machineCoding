package trello.service.impl

import trello.models.Board
import trello.models.CardList
import trello.models.User
import trello.service.BoardService
import trello.service.CardListService

class BoardServiceImpl: BoardService {

    private val cardListService: CardListService = CardListServiceImpl()
    private val boardRepository = mutableListOf<Board>()

    override fun createBoard(name: String): Board {

        val newBoard = Board(
            name = name
        )

        boardRepository.add(newBoard)

        return newBoard
    }

    override fun getBoard(boardId: String): Board? {
        return boardRepository.firstOrNull { it.id == boardId }
    }

    override fun addMember(user: User, boardId: String): Boolean {
        try {
            val board = getBoard(boardId) ?: throw Exception("INVALID BOARD")

            board.members?.add(user)
            println(board.members)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun removeMember(user: User, boardId: String): Boolean {
        try {
            val board = getBoard(boardId) ?: throw Exception("INVALID BOARD")

            board.members?.removeIf { it.id == user.id }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun deleteBoard(boardId: String): Boolean {
        return try {
            val board = getBoard(boardId) ?: throw Exception("INVALID BOARD")
            deleteAllList(board.listHolder!!)

            boardRepository.removeIf { it.id == boardId }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun printBoard(boardId: String?) {
        if (boardId != null) {
            val board = getBoard(boardId) ?: throw Exception("INVALID BOARD")
            println(board)
        } else {
            if (boardRepository.size == 0) println("No boards")
            else {
                boardRepository.map {
                    println(it)
                }
            }
        }
    }

    override fun addList(boardId: String, cardList: CardList): Boolean {
        return try {
            val board = getBoard(boardId) ?: throw Exception("INVALID BOARD")
            board.listHolder.add(cardList)
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }

    override fun removeList(boardId: String, cardList: CardList): Boolean {
        return try {
            val board = getBoard(boardId) ?: throw Exception("INVALID BOARD")
            board.listHolder.removeIf { it.id == cardList.id }
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }

    private fun deleteAllList(list: MutableList<CardList>) {
        list.map {
            cardListService.deleteCardList(it.id)
        }
    }
}