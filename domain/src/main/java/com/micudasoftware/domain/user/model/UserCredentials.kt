package com.micudasoftware.domain.user.model

/**
 * Data class representing user credentials.
 *
 * @property login The user's login.
 * @property password The user's password.
 */
data class UserCredentials(
    val login: String,
    val password: String,
)
