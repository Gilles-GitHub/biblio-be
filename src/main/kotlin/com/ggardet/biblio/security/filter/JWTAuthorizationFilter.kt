package com.ggardet.biblio.security.filter

import com.ggardet.biblio.config.YAMLConfig
import io.jsonwebtoken.Jwts
import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter(authManager: AuthenticationManager, private val yamlConfig: YAMLConfig) : BasicAuthenticationFilter(authManager) {

    @Throws(IOException::class, ServletException::class)
        override fun doFilterInternal(req: HttpServletRequest,
                                            res: HttpServletResponse,
                                            chain: FilterChain) {
        val header = req.getHeader(yamlConfig.authorization)
        if (header == null || !header.startsWith(yamlConfig.bearer + StringUtils.SPACE)) {
            chain.doFilter(req, res)
            return
        }
        val authentication = getAuthentication(req)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(req, res)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(yamlConfig.authorization)
        val user = token?.let {
            Jwts.parser()
                    .setSigningKey(yamlConfig.secret)
                    .parseClaimsJws(token.replace(yamlConfig.bearer + StringUtils.SPACE, StringUtils.EMPTY))
                    .body
                    .subject
        }

        return user?.let { UsernamePasswordAuthenticationToken(user, null, ArrayList<GrantedAuthority>()) }
    }

}