package com.ggardet.biblio.security.config

import com.ggardet.biblio.security.service.UserAuthenticationService
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.security.core.userdetails.UsernameNotFoundException





@Component
class TokenAuthenticationProvider : AbstractUserDetailsAuthenticationProvider {

    @NonNull
    var userAuthenticationService: UserAuthenticationService? = null

    constructor() : super()

    constructor(auth: UserAuthenticationService?) : super() {
        this.userAuthenticationService = auth
    }

    override fun retrieveUser(username: String?, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken?): UserDetails {
        val credentials = usernamePasswordAuthenticationToken?.credentials
        val token: String? = 

                // TODO : to be finished
        return token? as? String
    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails?, authentication: UsernamePasswordAuthenticationToken?) {
        // Nothing to do
    }

}