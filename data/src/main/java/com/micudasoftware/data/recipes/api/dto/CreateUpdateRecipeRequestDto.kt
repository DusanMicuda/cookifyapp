package com.micudasoftware.data.recipes.api.dto

/**
 * Dto for creating a new recipe.
 *
 * @property name The name of the recipe.
 * @property ingredients The list of ingredients required for the recipe. Each ingredient is represented by an instance of [IngredientDto].
 * @property preparation The preparation instructions for the recipe.
 * @property photos The list of URLs of photos related to the recipe.
 */
data class CreateUpdateRecipeRequestDto(
    val name: String,
    val ingredients: List<IngredientDto>,
    val preparation: String,
    val photos: List<String>,
)
