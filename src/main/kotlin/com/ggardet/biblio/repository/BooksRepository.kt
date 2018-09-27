package com.ggardet.biblio.repository

import com.ggardet.biblio.model.Book
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface BooksRepository : MongoRepository<Book, String> {

    override fun findAll(): MutableList<Book> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}