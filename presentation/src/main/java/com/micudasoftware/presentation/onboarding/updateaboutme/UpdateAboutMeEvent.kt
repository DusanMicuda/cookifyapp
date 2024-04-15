package com.micudasoftware.presentation.onboarding.updateaboutme

import com.micudasoftware.presentation.common.UIEvent

/**
 * A sealed class representing update about me events in the onboarding process.
 * This class has two subclasses representing different types of update about me events.
 */
sealed class UpdateAboutMeEvent: UIEvent() {

    /**
     * Represents an update about me event with a specific about me text.
     * @property aboutMe The about me text to update.
     */
    data class UpdateAboutMe(val aboutMe: String): UpdateAboutMeEvent()

    /**
     * Represents a skip event in the update about me process.
     */
    data object Skip: UpdateAboutMeEvent()
}