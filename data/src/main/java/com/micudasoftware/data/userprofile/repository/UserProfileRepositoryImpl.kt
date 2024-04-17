package com.micudasoftware.data.userprofile.repository

import com.micudasoftware.data.userprofile.datasource.UserProfileDataSource
import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.userprofile.model.UserProfile
import com.micudasoftware.domain.userprofile.repository.UserProfileRepository

/**
 * The implementation of [UserProfileRepository].
 *
 * @property dataSource The remote user profile data source.
 */
class UserProfileRepositoryImpl(
    private val dataSource: UserProfileDataSource,
): UserProfileRepository {

    override suspend fun getUserProfile(userId: String): Result<UserProfile> =
        dataSource.getUserProfile(userId)
            .map { it.toUserProfile() }

    override suspend fun updateUserProfile(userProfile: UserProfile): Result<Unit> =
        dataSource.updateUserProfile(userProfile.toDto())
}
