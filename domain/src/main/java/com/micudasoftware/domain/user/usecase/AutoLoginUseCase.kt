package com.micudasoftware.domain.user.usecase

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.user.repository.UserRepository

/**
 * Use case to auto login user.
 *
 * @property userRepository The repository for user data and operations.
 */
class AutoLoginUseCase(
    private val userRepository: UserRepository,
) {

    /**
     * Function to auto login user.
     *
     * @return The empty [Result].
     */
    suspend operator fun invoke(): Result<Unit> =
        authenticateUser().chainError {
            userRepository.getCredentials()?.let { credentials ->
                userRepository.login(credentials.login, credentials.password)
            } ?: it
        }

    /**
     * Function to authenticate user if logged in.
     *
     * @return The empty [Result].
     */
    private suspend fun authenticateUser(): Result<Unit> =
        if (userRepository.isLoggedIn()) {
            userRepository.authenticate()
        } else {
            Result.Error(message = "User isn't logged in!")
        }
}
