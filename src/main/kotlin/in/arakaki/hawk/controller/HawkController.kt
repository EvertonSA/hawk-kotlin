package `in`.arakaki.hawk.controller

import `in`.arakaki.hawk.model.Breed
import `in`.arakaki.hawk.model.Tweet
import `in`.arakaki.hawk.model.User
import `in`.arakaki.hawk.service.BreedsService
import `in`.arakaki.hawk.service.TweetService
import `in`.arakaki.hawk.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value= ["/api/"])
class HawkController {

    /* Since I have problems getting info from twitter, lets use some cats */
    @Autowired
    lateinit var breedsService: BreedsService

    @GetMapping("/breeds")
    fun getAllBreeds(): Iterable<Breed>? {
        return breedsService.getAll();
    }

    @GetMapping("/fetchBreeds")
    fun fetchBreeds(): Iterable<Breed>? {
        return breedsService.fetchAndSaveBreeds();
    }

    /* ideally would be a delete mapping but I have lost to much time already  */
    @GetMapping("/deleteBreeds")
    fun deleteBreeds() {
        return breedsService.removeAll();
    }

    /* End cats controller paths */

    @Autowired
    lateinit var tweetService: TweetService

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/tweets")
    fun getAllTweets(): List<Tweet> {
        return tweetService.getAllTweets()
    }

    @GetMapping("/tweets/{hashtag}")
    fun getAllTweetsByHashtag(@PathVariable hashtag: String): List<Tweet> {
        println(hashtag)
        return tweetService.getAllTweetsByHashtag(hashtag)
    }

//    @GetMapping("/fetchTweets")
//    fun fetchTweets(): List<Tweet>? {
//        return tweetConsumer.fetchTweets()
//    }

    @GetMapping("/user")
    fun getAllUsers(): List<User>{
        return userService.getAllUsers()
    }

}