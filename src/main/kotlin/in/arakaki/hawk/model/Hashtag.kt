package `in`.arakaki.hawk.model

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hashtag(
        @SerialName("text") val text: String = ""
)