package com.ggardet.biblio.repository

import com.ggardet.biblio.entity.Book
import org.springframework.data.mongodb.repository.MongoRepository

interface BooksRepository : MongoRepository<Book, String>