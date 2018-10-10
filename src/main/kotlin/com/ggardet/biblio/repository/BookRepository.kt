package com.ggardet.biblio.repository

import com.ggardet.biblio.entity.BookEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface BookRepository : MongoRepository<BookEntity, String>