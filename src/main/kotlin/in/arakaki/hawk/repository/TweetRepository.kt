package `in`.arakaki.hawk.repository

import `in`.arakaki.hawk.model.Tweet
import org.springframework.data.mongodb.repository.MongoRepository

interface TweetRepository : MongoRepository<Tweet, String> {

}