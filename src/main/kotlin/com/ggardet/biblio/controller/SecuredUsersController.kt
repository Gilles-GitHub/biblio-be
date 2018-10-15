package com.ggardet.biblio.controller

import com.ggardet.biblio.security.entity.UserEntity
import com.ggardet.biblio.security.service.UserAuthenticationService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class SecuredUsersController {

    var authentication: UserAuthenticationService? = null

    @GetMapping("/current")
    fun getCurrent(@AuthenticationPrincipal user: UserEntity): UserEntity {
        return user
    }

    @GetMapping("/logout")
    fun logout(@AuthenticationPrincipal user: UserEntity): Boolean {
        authentication!!.logout(user)
        return true
    }

}