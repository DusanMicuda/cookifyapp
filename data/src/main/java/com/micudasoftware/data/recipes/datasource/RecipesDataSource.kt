package com.micudasoftware.data.recipes.datasource

import com.micudasoftware.data.common.ApiCaller
import com.micudasoftware.data.recipes.api.RecipesApi
import com.micudasoftware.data.recipes.api.dto.CreateUpdateRecipeRequestDto
import com.micudasoftware.data.recipes.api.dto.RateRecipeRequestDto
import com.micudasoftware.data.recipes.api.dto.RecipeDto
import com.micudasoftware.domain.common.Result

/**
 * Data source for recipes.
 *
 * @property recipesApi The API for recipes.
 */
class RecipesDataSource(
    private val recipesApi: RecipesApi
) {

    /**
     * Function to get latest recipes.
     *
     * @param count The number of recipes to get.
     * @param offset The offset for pagination.
     * @param regex The regex for filtering recipes.
     * @return The [Result] with list of [RecipeDto].
     */
    suspend fun getLatestRecipes(
        count: Int = 10,
        offset: Int = 0,
        regex: String? = null
    ): Result<List<RecipeDto>> =
        ApiCaller.callResult<List<RecipeDto>>(
            apiCall = { recipesApi.getLatestRecipes(count, offset, regex) },
            errorMessage = { "Failed to get latest recipes!" }
        )

    /**
     * Function to get recipe by ID.
     *
     * @param recipeId The ID of the recipe.
     * @return The [Result] with [RecipeDto].
     */
    suspend fun getRecipe(recipeId: String): Result<RecipeDto> =
        ApiCaller.callResult<RecipeDto>(
            apiCall = { recipesApi.getRecipe(recipeId) },
            errorMessage = { "Failed to get recipe!" }
        )

    /**
     * Function to create a new recipe.
     *
     * @param dto The DTO for creating a new recipe.
     * @return The empty [Result].
     */
    suspend fun createRecipe(dto: CreateUpdateRecipeRequestDto) =
        ApiCaller.callResult(
            apiCall = { recipesApi.createRecipe(dto) },
            errorMessage = { "Failed to create recipe!" }
        )

    /**
     * Function to update a recipe.
     *
     * @param recipeId The ID of the recipe to update.
     * @param dto The DTO for updating the recipe.
     * @return The empty [Result].
     */
    suspend fun updateRecipe(recipeId: String, dto: CreateUpdateRecipeRequestDto) =
        ApiCaller.callResult(
            apiCall = { recipesApi.updateRecipe(recipeId, dto) },
            errorMessage = { "Failed to update recipe!" }
        )

    /**
     * Function to rate a recipe.
     *
     * @param dto The DTO for rating a recipe.
     * @return The empty [Result].
     */
    suspend fun rateRecipe(dto: RateRecipeRequestDto) =
        ApiCaller.callResult(
            apiCall = { recipesApi.rateRecipe(dto) },
            errorMessage = { "Failed to rate recipe!" }
        )
}
