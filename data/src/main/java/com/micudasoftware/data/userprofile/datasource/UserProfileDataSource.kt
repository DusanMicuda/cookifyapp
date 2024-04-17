package com.micudasoftware.data.userprofile.datasource

import com.micudasoftware.data.common.ApiCaller
import com.micudasoftware.data.userprofile.api.UserProfileApi
import com.micudasoftware.data.userprofile.api.dto.GetUserProfileResponseDto
import com.micudasoftware.data.userprofile.api.dto.UpdateUserProfileRequestDto
import com.micudasoftware.domain.common.Result

/**
 * Data source for user profile operations.
 *
 * @property userProfileApi The API for user profile operations.
 */
class UserProfileDataSource(
    private val userProfileApi: UserProfileApi
) {

    suspend fun getUserProfile(userId: String): Result<GetUserProfileResponseDto> =
        ApiCaller.callResult<GetUserProfileResponseDto>(
            apiCall = { userProfileApi.getUserProfile(userId) },
            errorMessage = { "Failed to get user profile!" }
        )

    suspend fun updateUserProfile(request: UpdateUserProfileRequestDto): Result<Unit> =
        ApiCaller.callResult(
            apiCall = { userProfileApi.updateUserProfile(request) },
            errorMessage = { "Failed to update user profile" }
        )
}
