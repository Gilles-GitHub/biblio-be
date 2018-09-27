package com.ggardet.biblio.service

import com.ggardet.biblio.model.Book
import com.ggardet.biblio.repository.BooksRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BooksService(@Autowired var booksRepository: BooksRepository) {

    fun books(): List<Book> = booksRepository.findAll()

}