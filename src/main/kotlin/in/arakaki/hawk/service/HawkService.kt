package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.model.Tweet

interface HawkService {

    fun getAllTweets() : List<Tweet>

    fun getAllTweetsByHashtag(hashtag: String) : List<Tweet>

    fun testTwitterAPI() : String
}