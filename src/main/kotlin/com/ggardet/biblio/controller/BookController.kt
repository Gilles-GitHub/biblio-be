package com.ggardet.biblio.controller

import com.ggardet.biblio.dto.Book
import com.ggardet.biblio.entity.BookEntity
import com.ggardet.biblio.mapper.BookMapper
import com.ggardet.biblio.repository.BookRepository
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600)
@RestController
class BookController(val bookRepository: BookRepository, val bookMapper: BookMapper) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

    /**
     * Provide all books of the collection all books
     *
     * @return the list of books present in the collection
     */
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Finds all the books of the collection")])
    @GetMapping("/books")
    fun getBooks(): ResponseEntity<List<BookEntity>> {
        logger.info("Provide all books of the collection")
        val responses: List<BookEntity> = bookRepository.findAll()
        return ResponseEntity.ok(responses)
    }

    /**
     * Provide a book from the collection
     *
     * @param id the book identifier
     * @return the book related to the given identifier
     */
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Finds the book from its code identifier")])
    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: String): ResponseEntity<BookEntity> {
        logger.info("Provide a book from the collection")
        val response: BookEntity = bookRepository.findById(id).orElseThrow { EntityNotFoundException("Book not found") }
        return ResponseEntity.ok(response)
    }

    /**
     * Insert a book into the collection
     *
     * @param request the HTTP request body
     * @return the updated book related to the given identifier
     */
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Create a book")])
    @PostMapping("/books")
    fun postBook(@RequestBody request: Book): ResponseEntity<BookEntity> {
        logger.info("Insert a book into the collection")
        val bookEntity = BookEntity()
        bookMapper.mapRequestToEntity(bookEntity, request)
        val response: BookEntity = bookRepository.insert(bookEntity)
        return ResponseEntity.ok(response)
    }

    /**
     * Update a book of the collection
     *
     * @param id the book identifier
     * @param request the HTTP request body
     */
    @ApiResponses(value = [ApiResponse(responseCode = "204", description = "Update a book find from its identifier")])
    @PutMapping("/books/{id}")
    fun putBook(@PathVariable("id") id: String, @RequestBody request: Book): ResponseEntity<Void> {
        logger.info("Update a book of the collection")
        val bookEntity: BookEntity = bookRepository.findById(id).orElseThrow { EntityNotFoundException("Book not found") }
        bookMapper.mapRequestToEntity(bookEntity, request)
        bookRepository.save(bookEntity)
        return ResponseEntity.noContent().build()
    }

    /**
     * Remove a book from the collection
     *
     * @param id the book identifier
     */
    @ApiResponses(value = [ApiResponse(responseCode = "204", description = "Delete a book from its code identifier")])
    @DeleteMapping("/books/{id}")
    fun deleteBook(@PathVariable("id") id: String): ResponseEntity<Void> {
        logger.info("Remove a book from the collection")
        val bookEntity: BookEntity = bookRepository.findById(id).orElseThrow { EntityNotFoundException("Book not found") }
        bookRepository.delete(bookEntity)
        return ResponseEntity.noContent().build()
    }

}
