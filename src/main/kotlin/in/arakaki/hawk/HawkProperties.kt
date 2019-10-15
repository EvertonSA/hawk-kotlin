package `in`.arakaki.hawk

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("twitter")
class TwitterProperties  : TwitterBaseUrlProperties() {
    lateinit var apiUrlOauth2: String
    lateinit var apiUrlSearch: String
    lateinit var credentials: String
}

abstract class TwitterBaseUrlProperties {
    lateinit var apiUrl: String
}