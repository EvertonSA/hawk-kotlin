package `in`.arakaki.hawk.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import kotlinx.serialization.*

@Document(collection = "users")
@Serializable
data class User (
        @Id
        @SerialName("id_str") val userId: String,
        @SerialName("name") val name: String,
        @SerialName("location") val location: String,
        @SerialName("lang") val lang: String,
        @SerialName("followers_count") val followers_count: Int
)