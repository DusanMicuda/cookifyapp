package com.micudasoftware.data.userprofile.api.dto

/**
 * Request to update user profile.
 *
 * @property userName The full name of the user.
 * @property aboutMeText The user's description of themselves.
 * @property titlePhotoUrl The URL of the user's title photo.
 * @property profilePhotoUrl The URL of the user's profile photo.
 */
data class UpdateUserProfileRequestDto(
    val userName: String,
    val aboutMeText: String? = null,
    val titlePhotoUrl: String? = null,
    val profilePhotoUrl: String? = null,
)
