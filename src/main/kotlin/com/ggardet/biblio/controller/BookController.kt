package com.ggardet.biblio.controller

import com.ggardet.biblio.model.Book
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.validation.Valid

@RestController
class BookController {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @GetMapping("/books/{userId}")
    fun getBooksOfUser(@PathVariable(value = "userId") @Valid userId: Int): ArrayList<Book> {
        logger.info("BookController")
        val response = arrayListOf<Book>()
        val book = Book(id = 123456, authorFirstName = "Victor", authorLastName = "Hugo", name = "Les misérables", publicationDate = LocalDate.parse("1862-03-30", DateTimeFormatter.ISO_DATE))
        response.add(book)
        logger.info(response.toString())

        return response
    }

}