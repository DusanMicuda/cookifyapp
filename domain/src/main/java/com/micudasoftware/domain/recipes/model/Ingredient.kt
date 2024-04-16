package com.micudasoftware.domain.recipes.model

/**
 * Data class representing ingredient.
 *
 * @property name The name of the ingredient.
 * @property quantity The required quantity.
 */
data class Ingredient(
    val name: String,
    val quantity: String,
)
