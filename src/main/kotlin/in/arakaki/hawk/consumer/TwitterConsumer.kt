package `in`.arakaki.hawk.consumer

import `in`.arakaki.hawk.model.Tweet
import okhttp3.*

import java.io.IOException

const val BASE_URL = "https://api.thecatapi.com/v1/breeds"

class TwitterConsumer {

    var client = OkHttpClient()

    fun fetchTweets(): List<Tweet>?{
        val request = Request.Builder()
                .url(BASE_URL + "")
                .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                println(response.body().string())
            }
            override fun onFailure(call: Call, e: IOException) {
                println("failed")
            }
        })
        return null
    }
}