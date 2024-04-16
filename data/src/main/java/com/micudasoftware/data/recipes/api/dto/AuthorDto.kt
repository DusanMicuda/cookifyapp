package com.micudasoftware.data.recipes.api.dto

/**
 * Data class represents author of the recipe.
 *
 * @property id The user's id.
 * @property name The user's name.
 * @property profilePhotoUrl The URL of the user's profile photo.
 */
data class AuthorDto(
    val id: String,
    val name: String,
    val profilePhotoUrl: String?,
)
