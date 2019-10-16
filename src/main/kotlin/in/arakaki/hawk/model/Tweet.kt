package `in`.arakaki.hawk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "tweets")
@Serializable
data class Tweet(
        @Id
        @SerialName("id_str") val id_str: String = "",
        @SerialName("user") val user: User,
        @SerialName("text") val text: String = "",
        @SerialName("entities") val entities: Entity
)

