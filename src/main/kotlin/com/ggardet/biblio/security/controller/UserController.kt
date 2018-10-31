package com.ggardet.biblio.security.controller

import com.ggardet.biblio.security.entity.ApplicationUserEntity
import com.ggardet.biblio.security.repository.ApplicationUserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController(private val applicationUserRepository: ApplicationUserRepository,
                     private val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody user: ApplicationUserEntity) {
        user.password = bCryptPasswordEncoder.encode(user.password)
        applicationUserRepository.save(user)
    }

}