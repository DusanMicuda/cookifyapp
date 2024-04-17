package com.micudasoftware.domain.user.repository

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.user.model.UserCredentials

/**
 * Repository for user data and operations.
 */
interface UserRepository {

    /**
     * Function to get user credentials.
     *
     * @return The user credentials if exists.
     */
    fun getCredentials(): UserCredentials?

    /**
     * Function to get Authorization token.
     *
     * @return The authorization token if exists.
     */
    fun getAuthorizationToken(): String?

    /**
     * Function to get User Id.
     *
     * @return The user id if exists.
     */
    fun getUserId(): String?

    /**
     * Function to check if user is logged in.
     *
     * @return True if user is logged in, otherwise false.
     */
    fun isLoggedIn(): Boolean

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
}
