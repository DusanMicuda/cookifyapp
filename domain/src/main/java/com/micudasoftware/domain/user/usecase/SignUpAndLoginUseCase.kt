package com.micudasoftware.domain.user.usecase

import com.micudasoftware.domain.user.repository.UserRepository

/**
 * Use case to sign up and login user.
 *
 * @property userRepository The repository for user data and operations.
 */
class SignUpAndLoginUseCase(
    private val userRepository: UserRepository,
) {

    /**
     * Function to sign up and login user.
     *
     * @param name The user's name.
     * @param email The user's email.
     * @param password The user's password.
     * @return The empty [Result].
     */
    suspend operator fun invoke(name: String, email: String, password: String) =
        userRepository.signup(name, email, password).chain {
            userRepository.login(email, password)
        }
}
