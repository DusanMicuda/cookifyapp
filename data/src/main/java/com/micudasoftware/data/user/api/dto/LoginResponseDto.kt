package com.micudasoftware.data.user.api.dto

/**
 * Dto for user login response.
 *
 * @property token The Authorization Token.
 */
data class LoginResponseDto(
    val token: String,
)
