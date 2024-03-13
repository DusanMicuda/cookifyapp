package com.micudasoftware.data.user.api.dto

/**
 * Dto for user login request.
 *
 * @property email The user email.
 * @property password The user password.
 */
data class LoginRequestDto(
    val email: String,
    val password: String,
)
