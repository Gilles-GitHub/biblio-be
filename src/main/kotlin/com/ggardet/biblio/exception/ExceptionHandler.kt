package com.ggardet.biblio.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler()

@ExceptionHandler(Exception::class)
fun processError(e: Exception): ResponseEntity<Any> {
    return ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
}

@ExceptionHandler(HttpMediaTypeNotSupportedException::class)
fun processError(e: HttpMediaTypeNotSupportedException): ResponseEntity<Any> {
    return ResponseEntity(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE)
}
