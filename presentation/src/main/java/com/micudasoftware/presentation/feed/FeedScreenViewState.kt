package com.micudasoftware.presentation.feed

import com.micudasoftware.presentation.common.UIState
import com.micudasoftware.presentation.feed.model.RecipeItemModel

data class FeedScreenViewState(
    val recipes: List<RecipeItemModel> = emptyList(),
    val profileImageUrl: String? = null,
    val bottomLoading: Boolean = false,
): UIState()
