package com.micudasoftware.presentation.onboarding

import com.micudasoftware.presentation.common.UIEvent

/**
 * Events for Onboarding screen.
 */
sealed class OnboardingEvent: UIEvent() {

    /**
     * Event to change name value.
     *
     * @property name The changed name.
     */
    data class ChangeName(val name: String): OnboardingEvent()

    /**
     * Event to change email value.
     *
     * @property email The changed email.
     */
    data class ChangeEmail(val email: String): OnboardingEvent()

    /**
     * Event to Change password value.
     *
     * @property password The changed password.
     */
    data class ChangePassword(val password: String): OnboardingEvent()

    /**
     * Event to login user.
     */
    data object Login: OnboardingEvent()

    /**
     * Event to sign up user.
     */
    data object SignUp: OnboardingEvent()

    /**
     * Event to switch screen state to login.
     */
    data object SwitchToLogin: OnboardingEvent()

    /**
     * Event to switch screen state to sign up.
     */
    data object SwitchToSignUp: OnboardingEvent()
}
