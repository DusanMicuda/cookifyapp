package com.micudasoftware.data.recipes.repository

import com.micudasoftware.data.recipes.api.dto.AuthorDto
import com.micudasoftware.data.recipes.api.dto.CreateUpdateRecipeRequestDto
import com.micudasoftware.data.recipes.api.dto.IngredientDto
import com.micudasoftware.data.recipes.api.dto.RecipeDto
import com.micudasoftware.domain.recipes.model.Author
import com.micudasoftware.domain.recipes.model.Ingredient
import com.micudasoftware.domain.recipes.model.Recipe

fun List<RecipeDto>.toRecipes(): List<Recipe> = map { it.toRecipe() }

fun RecipeDto.toRecipe(): Recipe =
    Recipe(
        id = id,
        timestamp = timestamp,
        author = author?.toAuthor(),
        name = name,
        ingredients = ingredients.map { it.toIngredient() },
        preparation = preparation,
        rating = rating,
        myRating = myRating,
        ratingCount = ratingCount,
        photos = photos,
    )

fun AuthorDto.toAuthor() =
    Author(
        id = id,
        name = name,
        profilePhotoUrl = profilePhotoUrl,
    )

fun IngredientDto.toIngredient(): Ingredient =
    Ingredient(
        name = name,
        quantity = quantity,
    )

fun Ingredient.toIngredientDto(): IngredientDto =
    IngredientDto(
        name = name,
        quantity = quantity,
    )

fun Recipe.toCreateUpdateRecipeRequestDto(): CreateUpdateRecipeRequestDto =
    CreateUpdateRecipeRequestDto(
        name = name,
        ingredients = ingredients.map { it.toIngredientDto() },
        preparation = preparation,
        photos = photos,
    )