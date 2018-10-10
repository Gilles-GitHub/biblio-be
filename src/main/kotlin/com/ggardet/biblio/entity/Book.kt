package com.ggardet.biblio.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.hateoas.ResourceSupport
import java.util.*

@Document(collection = "books")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Book(@Id @JsonProperty("_id") val id: String,
                @JsonProperty("firstName") val authorFirstName: String,
                @JsonProperty("lastName") val authorLastName: String,
                @JsonProperty("bookName") val name: String,
                val addedDate: Date,
                val genre: String,
                val publicationDate: Date): ResourceSupport()