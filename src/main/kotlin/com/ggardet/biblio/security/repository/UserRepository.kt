package com.ggardet.biblio.security.repository

import com.ggardet.biblio.security.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<UserEntity, String>{
}