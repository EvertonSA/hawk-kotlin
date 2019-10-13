package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.model.User
import `in`.arakaki.hawk.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("UserService")
class UserServiceImp : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

}