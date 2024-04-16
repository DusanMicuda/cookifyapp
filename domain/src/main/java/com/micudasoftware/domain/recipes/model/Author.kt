package com.micudasoftware.domain.recipes.model

/**
 * Data class representing author of a recipe.
 *
 * @property id The unique identifier of the author.
 * @property name The name of the author.
 * @property profilePhotoUrl The URL of the author's profile photo.
 */
data class Author(
    val id: String,
    val name: String,
    val profilePhotoUrl: String?,
)
