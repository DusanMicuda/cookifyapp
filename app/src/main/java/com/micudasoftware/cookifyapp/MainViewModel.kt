package com.micudasoftware.cookifyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micudasoftware.domain.user.usecase.AutoLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * The Main ViewModel.
 *
 * @property autoLogin The use case to auto login the user.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val autoLogin: AutoLoginUseCase,
): ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        autoLoginUser()
    }

    /**
     * Function to automatic login the user with saved credentials.
     */
    private fun autoLoginUser() {
        viewModelScope.launch {
            autoLogin().onSuccess {
                _isLoggedIn.update { true }
            }.onError {
                Timber.e(it.throwable, "Auto login failed: ${it.message}")
            }.onFinished {
                _isLoading.update { false }
            }
        }
    }
}
