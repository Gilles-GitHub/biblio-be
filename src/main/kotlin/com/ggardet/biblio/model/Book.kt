package com.ggardet.biblio.model

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Getter
import lombok.Setter
import lombok.ToString
import org.springframework.data.annotation.Id
import java.time.LocalDate

@Getter
@Setter
@ToString
data class Book(@Id @JsonIgnore val id: Int,
                val name: String,
                val authorFirstName: String,
                val authorLastName: String,
                val genre: String,
                val publicationDate: Int,
                val addedDate: LocalDate)