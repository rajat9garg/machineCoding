package trello.service

import trello.models.User

interface UserService {

    fun createUser(name: String, email: String): User

    fun getUser(id: String): User?
}