package com.micudasoftware.presentation.onboarding.updateaboutme

import com.micudasoftware.presentation.common.UIState

/**
 * Data class representing the view state of the UpdateAboutMe screen.
 *
 * @property isLoading The flag that indicates if loading animation should be shown.
 */
data class UpdateAboutMeViewState(
    val isLoading: Boolean = false,
): UIState()
