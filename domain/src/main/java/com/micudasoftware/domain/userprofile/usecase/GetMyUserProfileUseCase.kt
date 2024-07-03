package com.micudasoftware.domain.userprofile.usecase

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.user.repository.UserRepository
import com.micudasoftware.domain.userprofile.model.UserProfile
import com.micudasoftware.domain.userprofile.repository.UserProfileRepository

class GetMyUserProfileUseCase(
    private val userRepository: UserRepository,
    private val userProfileRepository: UserProfileRepository,
) {

    suspend operator fun invoke(): Result<UserProfile> {
        val userId = userRepository.getUserId() ?: return Result.Error(message = "User id is null!")
        return userProfileRepository.getUserProfile(userId)
    }
}
