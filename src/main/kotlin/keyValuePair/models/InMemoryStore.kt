package keyValuePair.models

data class InMemoryStore(
    val store: MutableMap<String, MutableMap<String, Any>> = mutableMapOf(),
    val attributeTypes: MutableMap<String, Class<*>> = mutableMapOf()
)
