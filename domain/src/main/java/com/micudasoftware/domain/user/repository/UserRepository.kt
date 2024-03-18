package com.micudasoftware.domain.user.repository

import com.micudasoftware.domain.common.Result

/**
 * Repository for user data and operations.
 */
interface UserRepository {

    /**
     * Function to get Authorization token.
     *
     * @return The authorization token if exists.
     */
    fun getAuthorizationToken(): String?

    /**
     * Function to login user.
     *
     * @param email The user's email.
     * @param password The user's password.
     * @return The empty [Result].
     */
    suspend fun login(email: String, password: String): Result<Unit>

    /**
     * Function to sign up new user.
     *
     * @param name The user's name.
     * @param email The user's email.
     * @param password The user's password.
     * @return The empty [Result].
     */
    suspend fun signup(name: String, email: String, password: String): Result<Unit>

    /**
     * Function to authenticate user.
     *
     * @return The empty [Result].
     */
    suspend fun authenticate(): Result<Unit>

    /**
     * Function authenticate and automatic login user with saved credentials.
     *
     * @return The empty [Result].
     */
    suspend fun autoLogin(): Result<Unit>
}
