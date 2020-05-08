package com.ggardet.biblio.mapper

import com.ggardet.biblio.dto.Book
import com.ggardet.biblio.entity.BookEntity
import org.springframework.stereotype.Component

@Component
class BookMapper {

    /**
     * Map the DTO request model to its related Entity model
     *
     * @param bookEntity the Entity POJO
     * @request request the DTO POJO
     */
    internal fun mapRequestToEntity(bookEntity: BookEntity, request: Book) {
        bookEntity.authorFirstName = request.author.firstName
        bookEntity.authorLastName = request.author.lastName
        bookEntity.genre = request.genre
        bookEntity.name = request.title
        bookEntity.publicationDate = request.publishedYear
    }

}
