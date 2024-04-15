package com.micudasoftware.data.user.api.dto

/**
 * Dto for user login response.
 *
 * @property userId The user id.
 * @property token The Authorization Token.
 */
data class LoginResponseDto(
    val userId: String,
    val token: String,
)
