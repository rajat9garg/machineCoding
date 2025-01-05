package keyValuePair.service


interface StoreService {

    fun put(key: String, attributes: MutableMap<String, Any>)

    fun get(key: String): MutableMap<String, Any>

    fun delete(key: String)

    fun search(key: String, value: Any): List<String>
}