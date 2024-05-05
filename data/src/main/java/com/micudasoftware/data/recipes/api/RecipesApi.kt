package com.micudasoftware.data.recipes.api

import com.micudasoftware.data.recipes.api.dto.CreateUpdateRecipeRequestDto
import com.micudasoftware.data.recipes.api.dto.RateRecipeRequestDto
import com.micudasoftware.data.recipes.api.dto.RecipeDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The recipes API.
 */
interface RecipesApi {

    @GET("/latestRecipes")
    suspend fun getLatestRecipes(
        @Query("count") count: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("regex") regex: String? = null,
    ): Response<List<RecipeDto>>

    @GET("/recipe/{recipeId}")
    suspend fun getRecipe(@Path("recipeId") recipeId: String): Response<RecipeDto>

    @POST("/recipe")
    suspend fun createRecipe(@Body recipeDto: CreateUpdateRecipeRequestDto): Response<Unit>

    @PUT("/recipe/{recipeId}")
    suspend fun updateRecipe(
        @Path("recipeId") recipeId: String,
        @Body recipeDto: CreateUpdateRecipeRequestDto
    ): Response<Unit>

    @PUT("/recipe")
    suspend fun rateRecipe(@Body rateRecipeRequestDto: RateRecipeRequestDto): Response<Unit>
}
