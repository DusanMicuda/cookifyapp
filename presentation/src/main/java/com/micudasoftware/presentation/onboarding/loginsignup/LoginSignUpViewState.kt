package com.micudasoftware.presentation.onboarding.loginsignup

import com.micudasoftware.presentation.common.UIState
import com.micudasoftware.presentation.common.model.ValidatedFieldModel

/**
 * View state data for LoginSignUpScreen.
 *
 * @property name The user's name.
 * @property email The user's email.
 * @property password The user's password.
 * @property isLoading The flag that indicates if loading animation should be shown.
 * @property screenState The state of screen.
 */
data class LoginSignUpViewState(
    val name: ValidatedFieldModel = ValidatedFieldModel(),
    val email: ValidatedFieldModel = ValidatedFieldModel(),
    val password: ValidatedFieldModel = ValidatedFieldModel(),
    val isLoading: Boolean = false,
    val screenState: LoginSignUpScreenState = LoginSignUpScreenState.Login,
) : UIState()
