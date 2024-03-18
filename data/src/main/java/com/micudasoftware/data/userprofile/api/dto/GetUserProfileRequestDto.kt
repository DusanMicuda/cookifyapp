package com.micudasoftware.data.userprofile.api.dto

/**
 * Request to get user profile.
 *
 * @property userId The requested profile owner's id.
 */
data class GetUserProfileRequestDto(
    val userId: String,
)
