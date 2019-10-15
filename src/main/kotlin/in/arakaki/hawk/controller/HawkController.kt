package `in`.arakaki.hawk.controller

import `in`.arakaki.hawk.TwitterProperties
import `in`.arakaki.hawk.model.Tweet
import `in`.arakaki.hawk.service.TwitterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value= ["/api/"])
class HawkController() {

    @Autowired
    lateinit var twitterService: TwitterService


    @GetMapping("/test")
    fun test(): String{
        return twitterService.testTwitterAPI()
    }

    @GetMapping("/fetch")
    fun getAndSaveAllTweetsByHashtag(): List<Tweet> {
        return twitterService.fetchTweetsByHashtags()
    }

//    @GetMapping("/tweets/{hashtag}")
//    fun getAllTweetsByHashtag(@PathVariable hashtag: String): List<Tweet> {
//        return tweetService.getAllTweetsByHashtag(hashtag)
//    }
//
//    @GetMapping("/user")
//    fun getAllUsers(): List<User>{
//        return userService.getAllUsers()
//    }
//
//    @GetMapping("/tweets")
//    fun getAllTweets(): List<Tweet> {
//        return tweetService.getAllTweets()
//    }

}