package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.model.Hashtag
import `in`.arakaki.hawk.model.Tweet
import `in`.arakaki.hawk.repository.TweetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.web.client.RestTemplate

@Service("TweetService")
class TweetServiceImp : TweetService {

    @Autowired
    lateinit var tweetRepository: TweetRepository

    override fun getAllTweetsByHashtag(hashtag: String): List<Tweet> {
        return tweetRepository.findNullableByHashtag(hashtag)
    }

    override fun getAllTweets(): List<Tweet> {
       return tweetRepository.findAll()
    }

    fun getTwitterDataAndInsertIntoDb(){
        val quote = RestTemplate().getForObject("http://gturnquist-quoters.cfapps.io/api/random", Tweet::class.java)
    }

}