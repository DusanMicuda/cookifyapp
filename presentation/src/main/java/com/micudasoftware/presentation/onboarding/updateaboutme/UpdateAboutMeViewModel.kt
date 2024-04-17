package com.micudasoftware.presentation.onboarding.updateaboutme

import androidx.lifecycle.viewModelScope
import com.micudasoftware.domain.userprofile.usecase.UpdateAboutMeUseCase
import com.micudasoftware.presentation.R
import com.micudasoftware.presentation.common.ComposeViewModel
import com.micudasoftware.presentation.common.model.ButtonModel
import com.micudasoftware.presentation.common.model.DialogModel
import com.micudasoftware.presentation.common.model.StringModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for the UpdateAboutMe screen.
 * This ViewModel is responsible for handling user interactions and updating the UI state.
 *
 * @property updateAboutMe The use case for updating the about me text.
 */
@HiltViewModel
class UpdateAboutMeViewModel @Inject constructor(
    private val updateAboutMeText: UpdateAboutMeUseCase,
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
        }
    }

    /**
     * Updates the about me text of the user.
     * @param aboutMe The new about me text.
     */
    private fun updateAboutMe(aboutMe: String) {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {

            updateAboutMeText(aboutMe)
                .onSuccess {
                    // Todo navigate to feed
                }.onError {
                    Timber.e(it.throwable, "Failed to update about me: ${it.code} - ${it.message}")
                    showErrorDialog()
                }.onFinished {
                    _viewState.update { it.copy(isLoading = false) }
                }
        }
    }

    /**
     * Function to show generic error dialog.
     */
    private fun showErrorDialog() {
        _viewState.update { state ->
            state.copy(
                dialog = DialogModel(
                    title = StringModel.Resource(R.string.title_something_went_wrong),
                    message = StringModel.Resource(R.string.text_error_occurred),
                    positiveButton = ButtonModel(
                        text = StringModel.Resource(R.string.button_understand),
                        onClick = { _viewState.update { it.copy(dialog = null) } }
                    ),
                    onDismiss = { _viewState.update { it.copy(dialog = null) } }
                )
            )
        }
    }
}