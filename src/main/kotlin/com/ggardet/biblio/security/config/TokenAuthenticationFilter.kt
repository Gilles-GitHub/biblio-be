package com.ggardet.biblio.security.config

import com.google.common.net.HttpHeaders
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * The TokenAuthenticationFilter is responsible of extracting the authentication token from the request headers
 * It takes the Authorization header value and attempts to extract the token from it
 */
class TokenAuthenticationFilter : AbstractAuthenticationProcessingFilter {

    private val BEARER = "Bearer"

    constructor(requiresAuthenticationRequestMatcher: RequestMatcher?) : super(requiresAuthenticationRequestMatcher)

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val param = request?.getHeader((HttpHeaders.AUTHORIZATION)) ?: request?.getParameter("t")
        val token = param?.removeSuffix(BEARER)?.trim() ?: throw BadCredentialsException("Missing Authentication Token")

        val auth = UsernamePasswordAuthenticationToken(token, token)

        return authenticationManager.authenticate(auth)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        super.successfulAuthentication(request, response, chain, authResult)
        chain!!.doFilter(request, response)
    }

}