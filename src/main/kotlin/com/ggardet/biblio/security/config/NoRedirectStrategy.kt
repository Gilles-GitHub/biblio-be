package com.ggardet.biblio.security.config

import org.springframework.security.web.RedirectStrategy
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class NoRedirectStrategy : RedirectStrategy {

    override fun sendRedirect(request: HttpServletRequest?, response: HttpServletResponse?, url: String?) {
        // As we’re securing a REST API in case of authentication failure
        // the server should not redirect to any error page.
        // The server will simply return an HTTP 401 (Unauthorized).
    }
}