package com.micudasoftware.presentation.onboarding.updateaboutme

import androidx.lifecycle.viewModelScope
import com.micudasoftware.domain.user.repository.UserRepository
import com.micudasoftware.domain.userprofile.repository.UserProfileRepository
import com.micudasoftware.presentation.common.ComposeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for the UpdateAboutMe screen.
 * This ViewModel is responsible for handling user interactions and updating the UI state.
 *
 * @property userRepository Repository for user related operations.
 * @property userProfileRepository Repository for user profile related operations.
 */
@HiltViewModel
class UpdateAboutMeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userProfileRepository: UserProfileRepository,
): ComposeViewModel<UpdateAboutMeViewState, UpdateAboutMeEvent>() {

    // Mutable state flow for the view state
    private val _viewState = MutableStateFlow(UpdateAboutMeViewState())
    // Publicly exposed immutable state flow for the view state
    override val viewState = _viewState.asStateFlow()

    /**
     * Handles the incoming events from the UI.
     * @param event The event from the UI.
     */
    override fun onEvent(event: UpdateAboutMeEvent) {
        when (event) {
            is UpdateAboutMeEvent.UpdateAboutMe -> updateAboutMe(event.aboutMe)
            UpdateAboutMeEvent.Skip -> TODO("Not yet implemented")
        }
    }

    /**
     * Updates the about me text of the user.
     * @param aboutMe The new about me text.
     */
    private fun updateAboutMe(aboutMe: String) {
        _viewState.value = UpdateAboutMeViewState(isLoading = true)
        viewModelScope.launch {
            val userId = userRepository.getUserId()
            if(userId == null) {
                _viewState.value = UpdateAboutMeViewState(isLoading = false)
                return@launch
            }

            userProfileRepository.getUserProfile(userId)
                .chain {
                    userProfileRepository.updateUserProfile(
                        it.copy(aboutMeText = aboutMe)
                    )
                }.onSuccess {
                    // Todo navigate to next screen
                }.onError {
                    Timber.e(it.throwable, "Failed to update about me: ${it.code} - ${it.message}")
                    // Todo show error dialog
                }.onFinished {
                    _viewState.value = UpdateAboutMeViewState(isLoading = false)
                }
        }
    }
}