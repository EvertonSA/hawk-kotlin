package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.model.Hashtag
import `in`.arakaki.hawk.model.Tweet

interface TweetService {

    fun getAllTweets() : List<Tweet>

    fun getAllTweetsByHashtag(hashtag: String) : List<Tweet>

}