package com.ggardet.biblio.security.service

import com.ggardet.biblio.security.repository.ApplicationUserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Collections.emptyList

@Service
class UserDetailsServiceImpl(private val applicationUserRepository: ApplicationUserRepository) : UserDetailsService {

    fun existsByUsername(username: String): Boolean {
        return applicationUserRepository.existsByUsername(username)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val applicationUser = applicationUserRepository.findByUsername(username)?: throw UsernameNotFoundException(username)
        return User(applicationUser.username, applicationUser.password, emptyList<GrantedAuthority>())
    }


}