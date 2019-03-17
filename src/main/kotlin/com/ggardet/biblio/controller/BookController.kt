package com.ggardet.biblio.controller

import com.ggardet.biblio.entity.BookEntity
import com.ggardet.biblio.repository.BookRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600)
@Api(value = "/books", description = "Operations about books")
@RestController
class BookController(var bookRepository: BookRepository) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @ApiOperation(
            value = "Finds all the books of the collection",
            notes = "Multiple status values can be provided with comma seperated strings")
    @GetMapping("/books")
    fun getBooks(): ResponseEntity<List<BookEntity>> {
        logger.info("Provide all books")
        var response: List<BookEntity> = bookRepository.findAll()
        // add hateoas links for every book of the collection (get one)
        response.forEach { book -> book.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BookController::class.java).getBook(book.id)).withRel("book")) }
        response.forEach { book -> book.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BookController::class.java).getBook(book.id)).withSelfRel()) }

        return ResponseEntity.ok(response)
    }

    @ApiOperation(
            value = "Finds the book from its code identifier")
    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: String): ResponseEntity<Optional<BookEntity>> {
        logger.info("Provide a book")
        var book: Optional<BookEntity> = bookRepository.findById(id)
        book.get().add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BookController::class.java).getBook(book.get().id)).withRel("book"))

        return ResponseEntity.ok(book)
    }

}