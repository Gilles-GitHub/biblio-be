package com.ggardet.biblio.security.config

import com.google.common.net.HttpHeaders
import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class TokenAuthenticationFilter: AbstractAuthenticationProcessingFilter {

    private val BEARER = "Bearer"

    constructor(requiresAuthenticationRequestMatcher: RequestMatcher?) : super(requiresAuthenticationRequestMatcher)

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val param = request?.getHeader((HttpHeaders.AUTHORIZATION))?: request?.getParameter("t")
        val token = StringUtils.removeStart(param, BEARER)


//        val token = ofNullable(param)
//                .map({ value -> removeStart(value, BEARER) })
//                .map(???({ it.trim({ it <= ' ' }) }))
//        .orElseThrow { BadCredentialsException("Missing Authentication Token") }

        val auth = UsernamePasswordAuthenticationToken(token, token)
        return authenticationManager.authenticate(auth)
    }
}