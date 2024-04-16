package com.micudasoftware.presentation.onboarding.updateaboutme

import com.micudasoftware.presentation.common.UIState
import com.micudasoftware.presentation.common.model.DialogModel

/**
 * Data class representing the view state of the UpdateAboutMe screen.
 *
 * @property isLoading The flag that indicates if loading animation should be shown.
 * @property dialog The dialog to be shown.
 */
data class UpdateAboutMeViewState(
    val isLoading: Boolean = false,
    val dialog: DialogModel? = null,
): UIState()
