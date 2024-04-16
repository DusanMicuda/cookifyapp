package com.micudasoftware.presentation.onboarding.updateaboutme

import com.micudasoftware.presentation.common.UIEvent

/**
 * A sealed class representing update about me events in the onboarding process.
 */
sealed class UpdateAboutMeEvent: UIEvent() {

    /**
     * Represents an update about me event with a specific about me text.
     * @property aboutMe The about me text to update.
     */
    data class UpdateAboutMe(val aboutMe: String): UpdateAboutMeEvent()
}