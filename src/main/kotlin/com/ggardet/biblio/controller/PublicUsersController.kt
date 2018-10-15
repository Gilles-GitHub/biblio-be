package com.ggardet.biblio.controller

import com.ggardet.biblio.security.entity.UserEntity
import com.ggardet.biblio.security.repository.UserRepository
import com.ggardet.biblio.security.service.UserAuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/public/users")
class PublicUsersController {

    var authentication: UserAuthenticationService? = null

    var users: UserRepository? = null

    @PostMapping("/register")
    fun register(@RequestParam("username") username: String,
                 @RequestParam("password") password: String): String {

        users!!.save(UserEntity(username, username, password))

        return login(username, password)
    }

    @PostMapping("/login")
    fun login(@RequestParam("username") username: String,
              @RequestParam("password") password: String): String {

        return authentication!!.login(username, password) ?: throw  RuntimeException("invalid login and/or password")
    }

}