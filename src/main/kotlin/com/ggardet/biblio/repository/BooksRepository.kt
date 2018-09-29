package com.ggardet.biblio.repository

import com.ggardet.biblio.model.Book
import org.springframework.data.mongodb.repository.MongoRepository

//@RepositoryRestResource(collectionResourceRel = "books", path = "books")
interface BooksRepository : MongoRepository<Book, String>