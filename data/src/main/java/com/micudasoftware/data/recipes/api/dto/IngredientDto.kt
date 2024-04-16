package com.micudasoftware.data.recipes.api.dto

/**
 * Data class representing ingredient.
 *
 * @property name The name of the ingredient.
 * @property quantity The required quantity.
 */
data class IngredientDto(
    val name: String,
    val quantity: String,
)
