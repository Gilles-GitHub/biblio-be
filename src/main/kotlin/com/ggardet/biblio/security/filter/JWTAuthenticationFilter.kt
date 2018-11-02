package com.ggardet.biblio.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.ggardet.biblio.config.YAMLConfig
import com.ggardet.biblio.security.entity.ApplicationUserEntity
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authManager: AuthenticationManager, private val yamlConfig: YAMLConfig) : UsernamePasswordAuthenticationFilter() {

    init {
        authenticationManager = authManager
    }

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(req: HttpServletRequest,
                                       res: HttpServletResponse): Authentication {
        try {
            val creds = ObjectMapper()
                    .readValue(req.inputStream, ApplicationUserEntity::class.java)

            return authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            creds.username,
                            creds.password,
                            ArrayList<GrantedAuthority>())
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest,
                                                    res: HttpServletResponse,
                                                    chain: FilterChain,
                                                    auth: Authentication) {

        val token = Jwts.builder()
                .setSubject((auth.principal as User).username)
                .setExpiration(Date(System.currentTimeMillis() + 864_000_000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, yamlConfig.secret)
                .compact()
        res.addHeader(yamlConfig.access_token, token)
    }

}