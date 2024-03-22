package com.micudasoftware.presentation.onboarding.loginsignup

import com.micudasoftware.presentation.common.UIEvent

/**
 * Events for LoginSignUpScreen.
 */
sealed class LoginSignUpEvent: UIEvent() {

    /**
     * Event to change name value.
     *
     * @property name The changed name.
     */
    data class ChangeName(val name: String): LoginSignUpEvent()

    /**
     * Event to change email value.
     *
     * @property email The changed email.
     */
    data class ChangeEmail(val email: String): LoginSignUpEvent()

    /**
     * Event to Change password value.
     *
     * @property password The changed password.
     */
    data class ChangePassword(val password: String): LoginSignUpEvent()

    /**
     * Event to login user.
     */
    data object Login: LoginSignUpEvent()

    /**
     * Event to sign up user.
     */
    data object SignUp: LoginSignUpEvent()

    /**
     * Event to switch screen state to login.
     */
    data object SwitchToLogin: LoginSignUpEvent()

    /**
     * Event to switch screen state to sign up.
     */
    data object SwitchToSignUp: LoginSignUpEvent()
}
