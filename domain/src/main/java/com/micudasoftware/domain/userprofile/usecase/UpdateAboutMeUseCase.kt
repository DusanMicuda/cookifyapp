package com.micudasoftware.domain.userprofile.usecase

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.user.repository.UserRepository
import com.micudasoftware.domain.userprofile.repository.UserProfileRepository

/**
 * Use case for updating the about me text of the user profile.
 *
 * @property userRepository The repository for user data.
 * @property userProfileRepository The repository for user profile data.
 */
class UpdateAboutMeUseCase(
    private val userRepository: UserRepository,
    private val userProfileRepository: UserProfileRepository,
) {

    /**
     * Updates the about me text of the user profile.
     *
     * @param aboutMe The new about me text.
     * @return The result of the operation.
     */
    suspend operator fun invoke(aboutMe: String): Result<Unit> {
        val userId = userRepository.getUserId()
            ?: return Result.Error(message = "User is not logged in!")

        return userProfileRepository.getUserProfile(userId).chain {
            userProfileRepository.updateUserProfile(it.copy(aboutMeText = aboutMe))
        }
    }
}
