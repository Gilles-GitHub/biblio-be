package com.ggardet.biblio.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    /**
     * Handle the exception of type Exception
     *
     * @param ex the exception
     * @return a ResponseEntity with an HTTP error status
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun processError(ex: Exception): ResponseEntity<Any> {
        return ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    /**
     * Handle the exception of type UsernameNotFoundException
     *
     * @param ex the exception
     * @return a ResponseEntity with an HTTP error status
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<Any> {
        return ResponseEntity(ex, HttpStatus.UNAUTHORIZED)
    }

    /**
     * Handle the exception of type HttpMessageNotWritableException
     *
     * @param headers the HTTP headers of the request
     * @param status the HTTP status of the request
     * @param request the HTTP request
     * @return a ResponseEntity with an HTTP error status
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    override fun handleHttpMessageNotWritable(ex: HttpMessageNotWritableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}