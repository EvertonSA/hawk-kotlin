package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.TwitterProperties
import `in`.arakaki.hawk.model.Tweet

interface TwitterService {

    fun getAllTweets() : List<Tweet>

    fun fetchTweetsByHashtags() : List<Tweet>

    fun testTwitterAPI() : String

    fun deleteAllTweets()
}