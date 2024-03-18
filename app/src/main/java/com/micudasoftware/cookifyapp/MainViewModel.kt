package com.micudasoftware.cookifyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micudasoftware.domain.user.repository.UserRepository
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
 * @property userRepository repository for user operations.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        autoLogin()
    }

    /**
     * Function to automatic login the user with saved credentials.
     */
    private fun autoLogin() {
        viewModelScope.launch {
            userRepository.autoLogin().onSuccess {
                _isLoggedIn.update { true }
            }.onError {
                Timber.e(it.throwable, "Auto login failed: ${it.message}")
            }.onFinished {
                _isLoading.update { false }
            }
        }
    }
}
