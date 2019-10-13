package `in`.arakaki.hawk.repository

import `in`.arakaki.hawk.model.Tweet
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface TweetRepository : MongoRepository<Tweet, String> {

    @Query("{'hashtag': ?0}")
    fun findNullableByHashtag(hashtag: String): List<Tweet>
}