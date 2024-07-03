package com.micudasoftware.presentation.feed.model

import com.micudasoftware.domain.recipes.model.Author
import com.micudasoftware.domain.recipes.model.Recipe

fun List<Recipe>.toRecipeItemModels(): List<RecipeItemModel> {
    return map { it.toRecipeItemModel() }
}

fun Recipe.toRecipeItemModel(): RecipeItemModel {
    return RecipeItemModel(
        title = name,
        imageUrl = photos.firstOrNull() ?: "",
        authorModel = author?.toAuthorModel() ?: AuthorModel("", ""),
        rating = rating.toFloat()
    )
}

fun Author.toAuthorModel(): AuthorModel {
    return AuthorModel(
        profileImageUrl = profilePhotoUrl ?: "", // Todo add default image
        name = name
    )
}