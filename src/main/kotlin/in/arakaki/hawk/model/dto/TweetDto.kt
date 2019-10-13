package `in`.arakaki.hawk.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TweetDto (

        @JsonProperty("id_str")
        val tweetId: String?,
        @JsonProperty("user.id_str")
        val userId: String?,
        @JsonProperty("text")
        val content: String?,
        @JsonProperty("entities.hasttags")
        val hashtags: List<String>? = listOf()
)