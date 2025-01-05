package keyValuePair

import keyValuePair.service.impl.StoreServiceImpl

// store which stores all the key value pair
// key always a string
// value will be an object with key value pair again but this time the value will be primary datatype

// Models
// intialized on application
// I need to store keys and there data types
//

fun main() {
    println("THIS IS  MACHINE CODING FOR KEY VALUE PAIR")

    val storeService = StoreServiceImpl()

    while (true) {
        try {
            val input = readlnOrNull()?.trim()?.split(" ") ?: throw IllegalAccessException("INVALID INPUT")

            val command = input[0]

            when(command) {
                "get" -> {
                    val key = input[1]
                    println(storeService.get(key))
                }
                "search" -> {
                    val key = input[1]
                    val value = input[2]
                    println(
                        storeService.search(key, value)
                    )
                }
                "put" -> {
                    val key = input[1]
                    val newMap = mutableMapOf<String, Any>()
                    if ((input.size - 2) % 2 != 0) {
                        println("Invalid input: Attributes and values must be in key-value pairs")
                    } else {
                        for (i in 2 until input.size step 2) {
                            newMap[input[i]] = input[i + 1] // Add key-value pairs to the map
                        }
                        val key = input[1] // The key is at index 1
                        storeService.put(key, newMap)
                    }
                    storeService.put(key, newMap)
                }
            }

        } catch (e: Exception) {
            println(e.message)
            continue
        }
    }
}