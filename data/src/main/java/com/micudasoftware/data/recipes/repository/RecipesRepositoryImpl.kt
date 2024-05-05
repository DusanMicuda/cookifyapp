package com.micudasoftware.data.recipes.repository

import com.micudasoftware.data.recipes.api.dto.RateRecipeRequestDto
import com.micudasoftware.data.recipes.datasource.RecipesDataSource
import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.recipes.model.Recipe
import com.micudasoftware.domain.recipes.repository.RecipesRepository

/**
 * Implementation of [RecipesRepository].
 *
 * @property recipesDataSource The data source for recipes.
 */
class RecipesRepositoryImpl(
    private val recipesDataSource: RecipesDataSource,
): RecipesRepository {

    override suspend fun getLatestRecipes(offset: Int): Result<List<Recipe>> =
        recipesDataSource.getLatestRecipes(offset = offset).map { it.toRecipes() }

    override suspend fun getRecipe(recipeId: String): Result<Recipe> =
        recipesDataSource.getRecipe(recipeId).map { it.toRecipe() }

    override suspend fun createRecipe(recipe: Recipe): Result<Unit> =
        recipesDataSource.createRecipe(recipe.toCreateUpdateRecipeRequestDto())

    override suspend fun updateRecipe(recipe: Recipe): Result<Unit> =
        recipesDataSource.updateRecipe(recipe.id, recipe.toCreateUpdateRecipeRequestDto())

    override suspend fun rateRecipe(recipeId: String, rating: Double): Result<Unit> =
        recipesDataSource.rateRecipe(RateRecipeRequestDto(recipeId, rating))
}
