package com.ggardet.biblio.controller

import com.ggardet.biblio.model.Book
import com.ggardet.biblio.service.BooksService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.validation.Valid

@RestController
class BookController(@Autowired  var booksService: BooksService) {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @GetMapping("/books")
    fun getBooks(): List<Book> {
        val books: List<Book>
        books = booksService.books()
        return books
    }

}