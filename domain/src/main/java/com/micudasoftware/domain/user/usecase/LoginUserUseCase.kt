package com.micudasoftware.domain.user.usecase

import com.micudasoftware.domain.user.repository.UserRepository

/**
 * Use case to login user.
 *
 * @property userRepository The repository for user data and operations.

 */
class LoginUserUseCase(
    private val userRepository: UserRepository,
) {

    /**
     * Invokes the use case.
     *
     * @param email The user's email.
     * @param password The user's password.
     */
    suspend operator fun invoke(email: String, password: String) =
        userRepository.login(email, password)
}
