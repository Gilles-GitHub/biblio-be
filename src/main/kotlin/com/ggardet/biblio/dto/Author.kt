package com.ggardet.biblio.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.validator.constraints.Length

@JsonInclude(JsonInclude.Include.NON_NULL)
class Author(
        @Length(max = 25) val firstName: String,
        @Length(max = 25) val lastName: String
)
