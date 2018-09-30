package com.ggardet.biblio.controller

import com.ggardet.biblio.model.Book
import com.ggardet.biblio.repository.BooksRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Api(value = "/books", description = "Operations about books")
@RestController
class BookController(var booksRepository: BooksRepository) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @ApiOperation(
            value = "Finds all the books of the collection",
            notes = "Multiple status values can be provided with comma seperated strings")
    @GetMapping("/books")
    fun getBooks(): ResponseEntity<List<Book>> {
        logger.info("Provide all books")
        var response: List<Book> = booksRepository.findAll()
        // add hateoas links for every book of the collection (get one)
        response.forEach { book -> book.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BookController::class.java).getBook(book.id)).withSelfRel()) }

        return ResponseEntity.ok(response)
    }


    @ApiOperation(
            value = "Finds the book from its code identifier")
    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: String): ResponseEntity<Optional<Book>> {
        logger.info("Provide a book")

        return ResponseEntity.ok(booksRepository.findById(id))
    }

}