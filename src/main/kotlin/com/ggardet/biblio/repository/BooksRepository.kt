package com.ggardet.biblio.repository

import com.ggardet.biblio.model.Book
import org.springframework.data.mongodb.repository.MongoRepository

interface BooksRepository : MongoRepository<Book, String> {
    override fun findAll(): List<Book>
}