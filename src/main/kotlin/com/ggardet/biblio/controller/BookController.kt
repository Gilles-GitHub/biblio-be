package com.ggardet.biblio.controller

import com.ggardet.biblio.entity.BookEntity
import com.ggardet.biblio.repository.BookRepository
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600)
@RestController
class BookController(var bookRepository: BookRepository) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Finds all the books of the collection")])
    @GetMapping("/books")
    fun getBooks(): ResponseEntity<List<BookEntity>> {
        logger.info("Provide all books")
        val response: List<BookEntity> = bookRepository.findAll()
        // add hateoas links for every book of the collection (get one)
        response.forEach { book -> book.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController::class.java).getBook(book.id)).withRel("book")) }
        response.forEach { book -> book.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController::class.java).getBook(book.id)).withSelfRel()) }
        return ResponseEntity.ok(response)
    }

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Finds the book from its code identifier")])
    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: String): ResponseEntity<Optional<BookEntity>> {
        logger.info("Provide a book")
        val book: Optional<BookEntity> = bookRepository.findById(id)
        book.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController::class.java).getBook(book.get().id)).withRel("book"))
        return ResponseEntity.ok(book)
    }

}