package com.micudasoftware.presentation.feed.model

data class RecipeItemModel(
    val title: String,
    val imageUrl: String,
    val authorModel: AuthorModel,
    val rating: Float,
)
