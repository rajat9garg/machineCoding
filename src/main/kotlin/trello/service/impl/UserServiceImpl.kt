package trello.service.impl

import trello.models.User
import trello.service.UserService

class UserServiceImpl: UserService {

    private val userRepository = mutableListOf<User>()

    override fun createUser(name: String, email: String): User {
        val newUser = User(
            name = name,
            email = email
        )
        userRepository.add(newUser)
        return  newUser
    }

    override fun getUser(id: String): User? {
        return userRepository.firstOrNull {
            user -> user.id == id
        }
    }
}
