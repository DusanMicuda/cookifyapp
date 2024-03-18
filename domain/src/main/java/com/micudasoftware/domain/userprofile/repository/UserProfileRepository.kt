package com.micudasoftware.domain.userprofile.repository

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.userprofile.model.UserProfile

/**
 * The repository for user profiles.
 */
interface UserProfileRepository {

    suspend fun getUserProfile(userId: String): Result<UserProfile>

    suspend fun updateUserProfile(userProfile: UserProfile): Result<Unit>
}
