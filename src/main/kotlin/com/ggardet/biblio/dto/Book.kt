package com.ggardet.biblio.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.validator.constraints.Length
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class Book(
        val author: Author,
        @Length(max = 100) var title: String,
        @Length(max = 50) var genre: String,
        var publishedYear: Date
)
