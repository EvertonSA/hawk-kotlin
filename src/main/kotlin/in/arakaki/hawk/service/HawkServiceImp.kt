package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.TwitterProperties
import `in`.arakaki.hawk.model.Tweet
import `in`.arakaki.hawk.repository.TweetRepository
import com.beust.klaxon.Klaxon

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import kotlinx.serialization.*
import java.util.*

import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.MediaType
import okhttp3.OkHttpClient
import java.io.StringReader

@Service("HawkService")
class HawkServiceImp : HawkService {

    @Autowired
    lateinit var tweetRepository: TweetRepository

    @Autowired
    lateinit var twitterProperties: TwitterProperties

    @ImplicitReflectionSerializer
    override fun fetchTweetsByHashtags(): List<Tweet> {
        //ideal is to respond responseerros objects...
        val list = TwitterConsumer(twitterProperties).fetchHashtagsTweetAPI() as MutableList
        tweetRepository.insert(list)
        return list
    }

    @ImplicitReflectionSerializer
    override fun deleteAllTweets() {
        //ideal is to respond responseerros objects...
        val list = TwitterConsumer(twitterProperties).fetchHashtagsTweetAPI() as MutableList
        tweetRepository.deleteAll()
    }

    override fun getAllTweets(): MutableList<Tweet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @ImplicitReflectionSerializer
    override fun testTwitterAPI(): String{
        return TwitterConsumer(twitterProperties).testTweetAPI()
    }
}



