package com.micudasoftware.domain.userprofile.usecase

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.user.repository.UserRepository
import com.micudasoftware.domain.userprofile.repository.UserProfileRepository

/**
 * Use case for saving the uploaded profile images.
 *
 * @property userRepository The repository for user data.
 * @property userProfileRepository The repository for user profile data.
 */
class SaveUploadedProfileImagesUseCase(
    private val userRepository: UserRepository,
    private val userProfileRepository: UserProfileRepository,
) {

    /**
     * Saves the uploaded profile images.
     *
     * @param profilePhotoUrl The URL of the new profile photo.
     * @param titlePhotoUrl The URL of the new title photo.
     * @return The result of the operation.
     */
    suspend operator fun invoke(
        profilePhotoUrl: String?,
        titlePhotoUrl: String?,
    ): Result<Unit> {
        val userId = userRepository.getUserId()
            ?: return Result.Error(message = "User is not logged in!")

        return userProfileRepository.getUserProfile(userId).chain {
            userProfileRepository.updateUserProfile(
                it.copy(
                    profilePhotoUrl = profilePhotoUrl ?: it.profilePhotoUrl,
                    titlePhotoUrl = titlePhotoUrl ?: it.titlePhotoUrl
                )
            )
        }
    }
}
