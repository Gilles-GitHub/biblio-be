package com.ggardet.biblio.model

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class Book (val id: Long, val name: String, val authorFirstName:String, val authorLastName:String)