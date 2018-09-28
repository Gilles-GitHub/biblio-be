package com.ggardet.biblio.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "books")
data class Book(@Id @JsonProperty("_id") val id: String,
                val authorFirstName: String,
                val authorLastName: String,
                val name: String,
                val addedDate: Date,
                val genre: String,
                val publicationDate: Date)