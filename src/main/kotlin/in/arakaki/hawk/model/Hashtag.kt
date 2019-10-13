package `in`.arakaki.hawk.model

data class Hashtag(
        val id: String,
        val value: String,
        val users: List<Hashtag> = listOf(),
        val tweets: List<Tweet> = listOf()
)