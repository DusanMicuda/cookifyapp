package com.micudasoftware.presentation.feed

import androidx.lifecycle.viewModelScope
import com.micudasoftware.domain.recipes.repository.RecipesRepository
import com.micudasoftware.domain.userprofile.usecase.GetMyUserProfileUseCase
import com.micudasoftware.presentation.common.ComposeViewModel
import com.micudasoftware.presentation.feed.model.toRecipeItemModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository,
    private val getMyUserProfile: GetMyUserProfileUseCase,
): ComposeViewModel<FeedScreenViewState, FeedScreenEvent>() {


    private val _viewState = MutableStateFlow(FeedScreenViewState())
    override val viewState = _viewState.asStateFlow()

    init {
        loadProfile()
        loadMoreRecipes()
    }

    override fun onEvent(event: FeedScreenEvent) {
        when (event) {
            FeedScreenEvent.LoadMoreRecipes -> loadMoreRecipes()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getMyUserProfile().onSuccess { userProfile ->
                _viewState.update { it.copy(profileImageUrl = userProfile.profilePhotoUrl) }
            }.onError {
                Timber.e(it.throwable, "Failed to load user profile: ${it.message}")
            }
        }
    }

    private fun loadMoreRecipes() {
        viewModelScope.launch {
            _viewState.update { it.copy(bottomLoading = true) }
            recipesRepository.getLatestRecipes(_viewState.value.recipes.size)
                .onSuccess { recipes ->
                    _viewState.update { it.copy(recipes = it.recipes + recipes.toRecipeItemModels()) }
                }.onError {
                    Timber.e(it.throwable, "Failed to load recipes: ${it.message}")
                }.onFinished {
                    _viewState.update { it.copy(bottomLoading = false) }
                }
        }
    }

}
