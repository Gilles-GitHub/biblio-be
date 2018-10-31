package com.ggardet.biblio.security.repository

import com.ggardet.biblio.security.entity.ApplicationUserEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface ApplicationUserRepository : MongoRepository<ApplicationUserEntity, Long> {
    fun findByUsername(username: String): ApplicationUserEntity
}