package com.micudasoftware.presentation.onboarding.loginsignup

import androidx.lifecycle.viewModelScope
import com.micudasoftware.domain.user.usecase.LoginUserUseCase
import com.micudasoftware.domain.user.usecase.SignUpAndLoginUseCase
import com.micudasoftware.presentation.R
import com.micudasoftware.presentation.common.ComposeViewModel
import com.micudasoftware.presentation.common.model.ButtonModel
import com.micudasoftware.presentation.common.model.DialogModel
import com.micudasoftware.presentation.common.model.NavEvent
import com.micudasoftware.presentation.common.model.StringModel
import com.micudasoftware.presentation.navigation.destinations.UploadProfileImagesScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel to login and sign up user.
 *
 * @property loginUser The use case to login user.
 * @property signUpAndLogin The use case to sign up and login user.
 */
@HiltViewModel
class LoginSignUpViewModel @Inject constructor(
    private val loginUser: LoginUserUseCase,
    private val signUpAndLogin: SignUpAndLoginUseCase,
) : ComposeViewModel<LoginSignUpViewState, LoginSignUpEvent>() {

    private val _viewState = MutableStateFlow(LoginSignUpViewState())
    override val viewState = _viewState.asStateFlow()

    override fun onEvent(event: LoginSignUpEvent) {
        Timber.d("onEvent($event)")
        when(event) {
            is LoginSignUpEvent.ChangeEmail -> changeEmail(event.email)
            is LoginSignUpEvent.ChangeName -> changeName(event.name)
            is LoginSignUpEvent.ChangePassword -> changePassword(event.password)
            LoginSignUpEvent.Login -> login()
            LoginSignUpEvent.SignUp -> signUp()
            LoginSignUpEvent.SwitchToLogin -> switchScreen(LoginSignUpScreenState.Login)
            LoginSignUpEvent.SwitchToSignUp -> switchScreen(LoginSignUpScreenState.SignUp)
        }
    }

    /**
     * Function to change name value.
     *
     * @param name The new value.
     */
    private fun changeName(name: String) =
        _viewState.update {
            it.copy(
                name = it.name.copy(
                    value = name,
                    error = if (it.name.isError()) {
                        getNameValidationError(name)
                    } else {
                        it.name.error
                    }
                )
            )
        }

    /**
     * Function to change email value.
     *
     * @param email The new value.
     */
    private fun changeEmail(email: String) =
        _viewState.update {
            it.copy(
                email = it.email.copy(
                    value = email,
                    error = if (it.email.isError()) {
                        getEmailValidationError(email)
                    } else {
                        it.email.error
                    }
                )
            )
        }

    /**
     * Function to change password value.
     *
     * @param password The new value.
     */
    private fun changePassword(password: String) =
        _viewState.update {
            it.copy(
                password = it.password.copy(
                    value = password,
                    error = if (it.password.isError()) {
                        getPasswordValidationError(password)
                    } else {
                        it.password.error
                    }
                )
            )
        }

    /**
     * Function to switch screen state and reset errors.
     *
     * @param state The new state.
     */
    private fun switchScreen(state: LoginSignUpScreenState) =
        _viewState.update {
            it.copy(
                name = it.name.copy(error = null),
                email = it.email.copy(error = null),
                password = it.password.copy(error = null),
                screenState = state
            )
        }

    /**
     * Function to get validation error message for name value.
     *
     * @param name The value to validate.
     * @return The error message or null if the value is valid.
     */
    private fun getNameValidationError(
        name: String = viewState.value.name.value
    ): Int? = when {
        name.isBlank() && viewState.value.screenState == LoginSignUpScreenState.SignUp ->
            R.string.text_field_required
        else -> null
    }

    /**
     * Function to get validation error message for email value.
     *
     * @param email The value to validate.
     * @return The error message or null if the value is valid.
     */
    private fun getEmailValidationError(
        email: String = viewState.value.email.value
    ): Int? = when {
        email.isBlank() -> R.string.text_field_required
        !email.matches(Regex(EMAIL_REGEX)) -> R.string.text_wrong_email
        else -> null
    }

    /**
     * Function to get validation error message for password value.
     *
     * @param password The value to validate.
     * @return The error message or null if the value is valid.
     */
    private fun getPasswordValidationError(
        password: String = viewState.value.password.value
    ): Int? = when {
        password.isBlank() -> R.string.text_field_required
        !password.matches(Regex(PASSWORD_REGEX)) && viewState.value.screenState == LoginSignUpScreenState.SignUp ->
            R.string.text_weak_password
        else -> null
    }

    /**
     * Function to validate all values and set error messages.
     */
    private fun validateForm() {
        _viewState.update {
            it.copy(
                name = it.name.copy(error = getNameValidationError()),
                email = it.email.copy(error = getEmailValidationError()),
                password = it.password.copy(error = getPasswordValidationError()),
            )
        }
    }

    /**
     * Function to check if form is valid.
     *
     * @return True if form is valid, otherwise false.
     */
    private fun isFormValid(): Boolean =
        viewState.value.name.isNotError() &&
                viewState.value.email.isNotError() &&
                viewState.value.password.isNotError()

    /**
     * Function to login user if the form is valid.
     */
    private fun login() {
        Timber.d("login()")
        validateForm()
        if (!isFormValid()) {
            return
        }

        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            loginUser(
                email = viewState.value.email.value,
                password = viewState.value.password.value
            ).onSuccess {
                // Todo navigate to feed
            }.onError { error ->
                Timber.e(error.throwable, "Login Failed: ${error.code} - ${error.message}")
                showErrorDialog()
            }.onFinished {
                _viewState.update { it.copy(isLoading = false) }
            }
        }
    }

    /**
     * Function to sign up user if the form is valid.
     */
    private fun signUp() {
        Timber.d("signUp()")
        validateForm()
        if (!isFormValid()) {
            return
        }

        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            signUpAndLogin(
                name = viewState.value.name.value,
                email = viewState.value.email.value,
                password = viewState.value.password.value
            ).onSuccess {
                navigate(NavEvent.To(UploadProfileImagesScreenDestination))
            }.onError {
                Timber.e(it.throwable, "Sign Up failed: ${it.code} - ${it.message}")
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

    private companion object {
        private const val EMAIL_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
        private const val PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"
    }
}
