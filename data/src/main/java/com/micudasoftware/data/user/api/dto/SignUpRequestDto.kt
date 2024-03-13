package com.micudasoftware.data.user.api.dto

/**
 * Dto for sign up request.
 *
 * @property name User name.
 * @property email User email.
 * @property password User password.
 */
data class SignUpRequestDto(
    val name: String,
    val email: String,
    val password: String,
)
