package `in`.arakaki.hawk.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "breeds")
data class Breed (
        @Id
        val id: String?,
        val name: String,
        val origin: String,
        val intelligence: Int
)