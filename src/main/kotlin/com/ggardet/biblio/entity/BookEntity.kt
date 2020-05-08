package com.ggardet.biblio.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.StringUtils
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "books")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class BookEntity(@Id @JsonProperty("_id") val id: String,
                      @JsonProperty("firstName") var authorFirstName: String,
                      @JsonProperty("lastName") var authorLastName: String,
                      @JsonProperty("bookName") var name: String,
                      val addedDate: Date,
                      var genre: String,
                      var publicationDate: Date) {
    constructor() : this(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Date(), StringUtils.EMPTY, Date())
}
