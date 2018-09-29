package com.ggardet.biblio.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.codehaus.jackson.annotate.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "books")
data class Book(@Id @JsonIgnore @JsonProperty("_id") val id: ObjectId?,
                @JsonProperty("firstName") val authorFirstName: String,
                @JsonProperty("lastName") val authorLastName: String,
                @JsonProperty("bookName") val name: String,
                val addedDate: Date,
                val genre: String,
                val publicationDate: Date)