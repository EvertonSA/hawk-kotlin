package `in`.arakaki.hawk.controller

import `in`.arakaki.hawk.TwitterProperties
import `in`.arakaki.hawk.model.Tweet
import `in`.arakaki.hawk.model.User
import `in`.arakaki.hawk.service.HawkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value= ["/api/"])
class HawkController( val twitterProperties: TwitterProperties) {

    @Autowired
    lateinit var tweetService: HawkService

    @GetMapping("/test")
    fun getAllUsers(): String{
        return tweetService.testTwitterAPI()
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