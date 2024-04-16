package com.micudasoftware.data.recipes.api.dto

/**
 * Dto for rating a recipe.
 *
 * @property recipeId The id of the recipe to rate.
 * @property rating The rating to assign to the recipe.
 */
data class RateRecipeRequestDto(
    val recipeId: String,
    val rating: Double,
)
