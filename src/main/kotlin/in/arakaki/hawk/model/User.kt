package `in`.arakaki.hawk.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User (
        val userId: String,
        val lang: String,
        val location: String,
        val name: String
)