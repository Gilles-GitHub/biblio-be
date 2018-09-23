package com.ggardet.biblio.controller

import com.ggardet.biblio.model.Book
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import javax.validation.Valid

@RestController
class BookController {

    @GetMapping("/books/{userId}")
    fun getBooksOfUser(@PathVariable(value = "userId") @Valid userId: Int): ArrayList<Book> {
        return ArrayList<Book>();
    }

}