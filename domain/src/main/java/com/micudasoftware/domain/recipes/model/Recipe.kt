package com.micudasoftware.domain.recipes.model

/**
 * Data class representing recipe.
 *
 * @property id The unique identifier of the recipe.
 * @property timestamp The timestamp of the recipe creation.
 * @property author The author of the recipe.
 * @property name The name of the recipe.
 * @property ingredients The list of ingredients.
 * @property preparation The preparation steps.
 * @property rating The average rating of the recipe.
 * @property myRating The user's rating of the recipe.
 * @property ratingCount The number of ratings.
 * @property photos The list of photo URLs.
 */
data class Recipe(
    val id: String,
    val timestamp: Long,
    val author: Author?,
    val name: String,
    val ingredients: List<Ingredient>,
    val preparation: String,
    val rating: Double,
    val myRating: Double?,
    val ratingCount: Int,
    val photos: List<String>,
)
