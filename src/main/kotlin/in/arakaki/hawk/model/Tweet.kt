package `in`.arakaki.hawk.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "tweets")
data class Tweet(

        @Id
        val tweetId: String?,
        val userId: String?,
        val content: String?,
        val hashtags: List<String>? = listOf()
)