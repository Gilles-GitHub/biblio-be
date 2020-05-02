package com.ggardet.biblio.controller

import com.ggardet.biblio.entity.BookEntity
import com.ggardet.biblio.repository.BookRepository
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600)
@RestController
class BookController(var bookRepository: BookRepository) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Finds all the books of the collection")])
    @GetMapping("/books")
    fun getBooks(): ResponseEntity<List<BookEntity>> {
        logger.info("Provide all books")
        val responses: List<BookEntity> = bookRepository.findAll()
        // add hateoas links for every book of the collection (get one)
        responses.forEach { book -> book.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController::class.java).getBook(book.id)).withRel("book")) }
        responses.forEach { book -> book.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController::class.java).getBook(book.id)).withSelfRel()) }
        return ResponseEntity.ok(responses)
    }

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Finds the book from its code identifier")])
    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: String): ResponseEntity<Optional<BookEntity>> {
        logger.info("Provide a book")
        val response: Optional<BookEntity> = bookRepository.findById(id)
        // add hateoas link
        response.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController::class.java).getBook(response.get().id)).withRel("book"))
        return ResponseEntity.ok(response)
    }

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Create a book")])
    @PostMapping("/books")
    fun postBook(@RequestBody request: BookEntity): ResponseEntity<BookEntity> {
        logger.info("Create a book")
        val response = bookRepository.save(request)
        // add hateoas link
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController::class.java).getBook(response.id)).withRel("book"))
        return ResponseEntity.ok(response)
    }

    @ApiResponses(value = [ApiResponse(responseCode = "204", description = "Update a book find from its identifier")])
    @PutMapping("/books/{id}")
    fun putBook(@PathVariable("id") id: String, @RequestBody request: BookEntity): ResponseEntity.HeadersBuilder<*> {
        logger.info("Update a book")
        val bookEntity: Optional<BookEntity> = bookRepository.findById(id)
        // fill the new properties values
        bookEntity.map { book: BookEntity? ->
            {
                book?.authorFirstName = request.authorFirstName
                book?.authorLastName = request.authorLastName
                book?.genre = request.genre
                book?.name = request.name
                book?.publicationDate = request.publicationDate
            }
        }
        val response = bookRepository.save(request)
        // add hateoas link
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController::class.java).getBook(response.id)).withRel("book"))
        return ResponseEntity.noContent()
    }

    @ApiResponses(value = [ApiResponse(responseCode = "204", description = "Delete a book from its code identifier")])
    @DeleteMapping("/books/{id}")
    fun deleteBook(@PathVariable("id") id: String): ResponseEntity.HeadersBuilder<*> {
        logger.info("Delete a book")
        val bookEntity = bookRepository.findById(id)
        bookRepository.delete(bookEntity.get())
        return ResponseEntity.noContent()
    }

}
