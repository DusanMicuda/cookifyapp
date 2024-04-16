package com.micudasoftware.data.recipes.api.dto

/**
 * Data class representing recipe DTO.
 *
 * @property id The id of the recipe.
 * @property timestamp The timestamp of creation.
 * @property author The author of recipe.
 * @property name The recipe name.
 * @property ingredients The ingredients represented as [List] of [Ingredient].
 * @property preparation The preparation guide.
 * @property rating The rating of the recipe.
 * @property myRating The rating provided from user that requests the recipe.
 * @property ratingCount The number of ratings.
 * @property photos The photos.
 */
data class RecipeDto(
    val id: String,
    val timestamp: Long,
    val author: AuthorDto?,
    val name: String,
    val ingredients: List<IngredientDto>,
    val preparation: String,
    val rating: Double,
    val myRating: Double?,
    val ratingCount: Int,
    val photos: List<String>,
)
