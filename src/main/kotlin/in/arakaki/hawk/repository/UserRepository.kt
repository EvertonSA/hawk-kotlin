package `in`.arakaki.hawk.repository

import `in`.arakaki.hawk.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

}