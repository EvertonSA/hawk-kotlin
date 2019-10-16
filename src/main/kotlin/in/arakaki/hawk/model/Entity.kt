package `in`.arakaki.hawk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entity(
        @SerialName("hashtags") val hashtags: List<Hashtag>
)