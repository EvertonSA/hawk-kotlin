package `in`.arakaki.hawk.controller

import `in`.arakaki.hawk.model.Tweet
import `in`.arakaki.hawk.service.HawkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value= ["/api/"])
class HawkController() {

    @Autowired
    lateinit var hawkService: HawkService

    @GetMapping("/test")
    fun test(): String{
        return hawkService.testTwitterAPI()
    }

    @GetMapping("/fetch")
    fun getAndSaveAllTweetsByHashtag(): List<Tweet> {
        return hawkService.fetchTweetsByHashtags()
    }

    @GetMapping("/delete")
    fun deleteAll() {
        return hawkService.deleteAllTweets()
    }

}