package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.model.Tweet
import `in`.arakaki.hawk.repository.TweetRepository
import com.beust.klaxon.Klaxon

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.util.*

import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.MediaType
import okhttp3.OkHttpClient

@Service("TweetService")
class HawkServiceImp : HawkService {

    @Autowired
    lateinit var tweetRepository: TweetRepository

    override fun getAllTweets(): List<Tweet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllTweetsByHashtag(hashtag: String): List<Tweet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @ImplicitReflectionSerializer
    override fun testTwitterAPI(): String{
        return TwitterConsumer.testTweetAPI()
    }
}

@Serializable
data class Token(
        val token_type: String,
        val access_token: String
)

private object TwitterConsumer {

    var apiUrl: String = "https://api.twitter.com"
    var apiUrlOauth2: String = "/oauth2/token"
    var apiUrlSearch: String = "/1.1/search/tweets.json?q=%23superbowl&result_type=recent"
    var apiCredentials: String = "---"

//    var apiUrl: String = "${twitter.apiUrl}"
//    var apiUrlOauth2: String = "${twitter.apiUrlOauth2}"
//    var apiUrlSearch: String = "${twitter.apiUrlSearch}"
//    var apiCredentials: String = "${twitter.credentials}"

    @ImplicitReflectionSerializer
    fun testTweetAPI(): String {
        val client = OkHttpClient()
        println("$apiUrl$apiUrlOauth2")
        println("$apiUrl$apiUrlSearch")
        val tokenRequest = Request.Builder()
                .url("$apiUrl$apiUrlOauth2")
                .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(apiCredentials.toByteArray()))
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "grant_type=client_credentials"))
                .build()
        val token = Klaxon().parse<Token>(client.newCall(tokenRequest).execute().body().string())
        println(token?.access_token)
        val tweetRequest = Request.Builder()
                .url("$apiUrl$apiUrlSearch")
                .addHeader("Authorization", "Bearer " + token?.access_token)
                .get()
                .build()
        return client.newCall(tweetRequest).execute().body().string()
    }
}



