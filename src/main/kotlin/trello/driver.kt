package trello

import trello.models.Operations
import trello.service.impl.BoardServiceImpl
import trello.service.impl.CardListServiceImpl
import trello.service.impl.CardServiceImpl
import trello.service.impl.UserServiceImpl

// User: Id, Name, email
// Board: Id, Name, privace, url, members and list
// List: Id, name, cards
// Card: Id, Name, Description and assigned user

// Operations
// Boards Create and delete add and remove members
// List create and delete list
// Card Create card and assign them to user




fun main() {
    println("Desiging trello for machine coding round practice")
    val boardService = BoardServiceImpl()
    val cardService = CardServiceImpl()
    val userService = UserServiceImpl()
    val cardListService = CardListServiceImpl()


    try {
        val user1 = userService.createUser(name = "Rajat", email = "rajt@gmail.com")
        val user2 = userService.createUser(name = "Rajat1", email = "rajt@gmail.com")
        val user3 = userService.createUser(name = "Rajat2", email = "rajt@gmail.com")
        val user4 = userService.createUser(name = "Rajat3", email = "rajt@gmail.com")

        // print boards
        println(
            boardService.printBoard(null)
        )

        val board1 = boardService.createBoard("MEESHO")


        // add users to board
        boardService.addMember(user1, board1.id)

        println(
            boardService.printBoard(null)
        )

        // add cardList
        val cardList1 = cardListService.createCardList(name = "cardList1")

        boardService.addList(board1.id, cardList1)

        println(
            boardService.printBoard(null)
        )

        // add card to cardList

        val card1 = cardService.createCard(name = "CARD1", description = "Description for card1")
        val card2 = cardService.createCard(name = "CARD2", description = "Description for card1")
        val card3 = cardService.createCard(name = "CARD2", description = "Description for card1")


        val c = cardService.assignUser(card1.id, user1)
        println(c)

        cardService.assignUser(card2.id, user2)
        cardService.assignUser(card3.id, user3)


        cardListService.addCard(cardList1.id, card1)
        cardListService.addCard(cardList1.id, card2)
        cardListService.addCard(cardList1.id, card3)


        println(
            boardService.printBoard(null)
        )

        // Delete cardList
        cardListService.deleteCardList(cardList1.id)

        println(
            boardService.printBoard(null)
        )


    } catch (e: Exception) {
        println(e.message)
    }

}