package com.ggardet.biblio.repository

import com.ggardet.biblio.model.Book
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
interface BooksRepository : MongoRepository<Book, String>