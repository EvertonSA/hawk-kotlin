package `in`.arakaki.hawk.service

import `in`.arakaki.hawk.model.User

interface UserService {

    fun getAllUsers() : List<User>

}