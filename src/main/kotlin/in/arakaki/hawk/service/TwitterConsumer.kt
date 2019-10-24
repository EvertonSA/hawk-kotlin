package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.TwitterProperties
import `in`.arakaki.hawk.model.Tweet
import com.beust.klaxon.Klaxon
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.stereotype.Service
import java.io.StringReader
import java.util.*

@Serializable
data class Token(
        val token_type: String,
        val access_token: String
)

class TwitterConsumer(twitterProperties: TwitterProperties) {

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