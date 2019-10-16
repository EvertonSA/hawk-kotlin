package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.TwitterProperties
import `in`.arakaki.hawk.model.Tweet
import `in`.arakaki.hawk.repository.TweetRepository
import com.beust.klaxon.Klaxon

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import kotlinx.serialization.*
import mu.KotlinLogging
import java.util.*

import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import java.io.StringReader

@Service("TweetService")
class HawkServiceImp : TwitterService {

    private val logger = KotlinLogging.logger {}

    @Autowired
    lateinit var tweetRepository: TweetRepository

    @Autowired
    lateinit var twitterProperties: TwitterProperties

    @ImplicitReflectionSerializer
    override fun fetchTweetsByHashtags(): List<Tweet> {
        //ideal is to respond responseerros objects...
        val list = TwitterConsumer(twitterProperties).fetchHashtagsTweetAPI() as MutableList
        logger.info("deletando ")
        tweetRepository.deleteAll()
        tweetRepository.insert(list)
        return list
    }

    override fun getAllTweets(): MutableList<Tweet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @ImplicitReflectionSerializer
    override fun testTwitterAPI(): String{
        return TwitterConsumer(twitterProperties).testTweetAPI()
    }
}

@Serializable
data class Token(
        val token_type: String,
        val access_token: String
)

private class TwitterConsumer(twitterProperties: TwitterProperties) {

    var apiUrl: String = twitterProperties.apiUrl
    var apiUrlOauth2: String = twitterProperties.apiUrlOauth2
    var apiUrlSearch: String = twitterProperties.apiUrlSearch
    var apiCredentials: String = twitterProperties.credentials
    var hashtags: List<String> = twitterProperties.hashtags.split(",")

    @ImplicitReflectionSerializer
    fun testTweetAPI(): String {
        val client = OkHttpClient()
        val tokenRequest = Request.Builder()
                .url("$apiUrl$apiUrlOauth2" )
                .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(apiCredentials.toByteArray()))
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "grant_type=client_credentials"))
                .build()
        val token = Klaxon().parse<Token>(client.newCall(tokenRequest).execute().body().string())
        val tweetRequest = Request.Builder()
                .url("$apiUrl$apiUrlSearch"+ "test" )
                .addHeader("Authorization", "Bearer " + token?.access_token)
                .get()
                .build()
        return client.newCall(tweetRequest).execute().body().string()
    }

    @ImplicitReflectionSerializer
    fun fetchHashtagsTweetAPI(): List<Tweet> {
        val client = OkHttpClient()
        val token = getToken(client)
        val listTweets: MutableList <Tweet> = mutableListOf()
        for ( hashtag in hashtags ){
            val klaxon = Klaxon()
            val strTweets = token?.let { getTweets(client, it, hashtag) }
            val tweets = klaxon
                    .parseJsonObject(StringReader(strTweets))
                    .array<Any>("statuses")
                    ?.let { klaxon.parseFromJsonArray<Tweet>(it) }
            if (tweets != null) listTweets += tweets
        }
        return listTweets
    }

    private fun getTweets(client: OkHttpClient, token: Token, hashtag: String?): String? {
        val tweetRequest = Request.Builder()
                .url("$apiUrl$apiUrlSearch$hashtag" )
                .addHeader("Authorization", "Bearer " + token?.access_token)
                .get()
                .build()
        return client.newCall(tweetRequest).execute().body().string()
    }

    private fun getToken(client: OkHttpClient): Token? {
        val tokenRequest = Request.Builder()
                .url("$apiUrl$apiUrlOauth2" )
                .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(apiCredentials.toByteArray()))
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "grant_type=client_credentials"))
                .build()
        return Klaxon().parse<Token>(client.newCall(tokenRequest).execute().body().string())
    }

}



