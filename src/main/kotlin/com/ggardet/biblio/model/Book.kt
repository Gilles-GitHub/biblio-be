package com.ggardet.biblio.model

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Getter
import lombok.Setter
import lombok.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "books")
@Getter
@Setter
@ToString
data class Book(@Id @JsonIgnore val _id: String ,
                val name: String,
                val authorFirstName: String,
                val authorLastName: String,
                val genre: String,
                val publicationDate: Date,
                val addedDate: Date)