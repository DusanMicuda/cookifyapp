package com.micudasoftware.domain.userprofile.repository

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.userprofile.model.UserProfile

/**
 * The repository for user profiles.
 */
interface UserProfileRepository {

    /**
     * Returns the user profile with the specified ID.
     *
     * @param userId The ID of the user.
     * @return The [Result] containing the [UserProfile].
     */
    suspend fun getUserProfile(userId: String): Result<UserProfile>

    /**
     * Updates the user profile.
     *
     * @param userProfile The user profile to update.
     * @return The [Result] containing the empty result.
     */
    suspend fun updateUserProfile(userProfile: UserProfile): Result<Unit>
}
