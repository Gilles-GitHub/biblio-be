package com.ggardet.biblio.controller

import com.ggardet.biblio.model.Book
import com.ggardet.biblio.repository.BooksRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(var booksRepository: BooksRepository) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @GetMapping("/books")
    fun getBooks(): List<Book> {
        logger.debug("Provide all books")
        return booksRepository.findAll()
    }

}