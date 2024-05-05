package com.micudasoftware.domain.recipes.repository

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.recipes.model.Recipe

/**
 * Repository for managing recipes.
 */
interface RecipesRepository {

    /**
     * Returns the latest recipes.
     *
     * @param offset The offset of the first recipe to return.
     * @return The [Result] containing the list of [Recipe].
     */
    suspend fun getLatestRecipes(offset: Int): Result<List<Recipe>>

    /**
     * Returns the recipe with the specified ID.
     *
     * @param recipeId The ID of the recipe.
     * @return The [Result] containing the [Recipe].
     */
    suspend fun getRecipe(recipeId: String): Result<Recipe>

    /**
     * Creates a new recipe.
     *
     * @param recipe The recipe to create.
     * @return The [Result] containing the empty result.
     */
    suspend fun createRecipe(recipe: Recipe): Result<Unit>

    /**
     * Updates an existing recipe.
     *
     * @param recipe The recipe to update.
     * @return The [Result] containing the empty result.
     */
    suspend fun updateRecipe(recipe: Recipe): Result<Unit>

    /**
     * Rates a recipe.
     *
     * @param recipeId The ID of the recipe.
     * @param rating The rating to assign to the recipe.
     * @return The [Result] containing the empty result.
     */
    suspend fun rateRecipe(recipeId: String, rating: Double): Result<Unit>
}
