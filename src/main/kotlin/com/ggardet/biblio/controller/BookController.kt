package com.ggardet.biblio.controller

import com.ggardet.biblio.repository.BooksRepository

//@RestController
class BookController(var booksRepository: BooksRepository) {

//    val logger: Logger = LoggerFactory.getLogger(this.javaClass.name)
//
//    @GetMapping("/books")
//    fun getBooks(): List<Book> {
//        logger.debug("Provide all books")
//        return booksRepository.findAll()
//    }
//
//    @GetMapping("/books/{id}")
//    fun getBook(@PathVariable("id") id: String): Optional<Book> {
//        logger.debug("Provide a book")
//        return booksRepository.findById(id)
//    }
//
//    @GetMapping("/books/author/{name}")
//    fun getBookByAuthorName(@PathVariable("name") name: String): List<Book> {
//        logger.debug("Provide a book")
//        return booksRepository.findByAuthorLastName(name)
//    }

}