package com.ggardet.biblio.security.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class ApplicationUserEntity(@Id @JsonProperty(value = "_id") var id: String?,
                                 @JsonProperty("username") var username: String,
                                 @JsonProperty("password") var password: String)