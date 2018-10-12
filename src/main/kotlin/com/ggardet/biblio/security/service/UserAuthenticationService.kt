package com.ggardet.biblio.security.service

import com.ggardet.biblio.security.entity.UserEntity

interface UserAuthenticationService {

    /**
     * Logs in with the given username and password
     *
     * @param username
     * @param password
     * @return a user when login succeeds
     */
    fun login(username: String, password: String): String?

    /**
     * Finds a user by its dao-key
     *
     * @param token user dao key
     * @return
     */
    fun findByToken(token: String): UserEntity?

    /**
     * Logs out the given input userEntity
     *
     * @param userEntity the userEntity to logout
     */
    fun logout(userEntity: UserEntity)

}