package com.micudasoftware.domain.userprofile.model

/**
 * The User profile.
 *
 * @property userId The unique identifier of the user.
 * @property userName The name of the user.
 * @property aboutMeText The user's description of themselves.
 * @property titlePhotoUrl The URL of the user's title photo.
 * @property profilePhotoUrl The URL of the user's profile photo.
 */
data class UserProfile(
    val userId: String,
    val userName: String,
    val aboutMeText: String?,
    val titlePhotoUrl: String?,
    val profilePhotoUrl: String?,
)
