package `in`.arakaki.hawk.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import kotlinx.serialization.*

@Document(collection = "tweets")
@Serializable
data class Tweet(
        @Id
        @SerialName("id_str") val tweetId: String,
        @SerialName("user") val user: User,
        @SerialName("text") val content: String,
        @SerialName("entities.hashtags") val hashtags: List<String>
)