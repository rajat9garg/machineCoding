package keyValuePair.service.impl

import keyValuePair.exceptions.InvalidDataType
import keyValuePair.exceptions.NoDataFOUND
import keyValuePair.models.InMemoryStore
import keyValuePair.service.StoreService

class StoreServiceImpl: StoreService {

    private val redis = InMemoryStore()

    override fun put(key: String, attributes: MutableMap<String, Any>) {
        val newValue = mutableMapOf<String, Any>()

        attributes.forEach {
            isValidDataType(it.key, it.value)

            registerNewKeyType(it.key, it.value)
            newValue[it.key] = it.value
        }
        redis.store[key] = newValue
        println(redis.store)
    }

    override fun get(key: String):MutableMap<String, Any> {
        return redis.store[key] ?: throw NoDataFOUND("KEY NOT FOUND")
    }

    override fun delete(key: String) {
        redis.store.remove(key)?: throw NoDataFOUND("KEY NOT FOUND")
    }

    override fun search(key: String, value: Any): List<String> {
        return redis.store.filter { it.value[key] == value }
            .map { it.key }
    }

    private fun registerNewKeyType(attributeKey: String, attributeValue: Any) {
        redis.attributeTypes[attributeKey] = attributeValue::class.java
    }

    private fun isValidDataType(attributeKey: String, value: Any) {
        val existingKey = redis.attributeTypes[attributeKey]

        if (existingKey != null && existingKey != value::class.java) {
            throw InvalidDataType("INVALID DATA TYPE FOR KEY $attributeKey")
        }
    }
}