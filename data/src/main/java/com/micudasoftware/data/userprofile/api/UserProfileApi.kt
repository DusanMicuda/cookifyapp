package com.micudasoftware.data.userprofile.api

import com.micudasoftware.data.userprofile.api.dto.GetUserProfileResponseDto
import com.micudasoftware.data.userprofile.api.dto.UpdateUserProfileRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Api for user profile operations.
 */
interface UserProfileApi {

    @GET("/profile/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): Response<GetUserProfileResponseDto>

    @POST("/profile")
    suspend fun updateUserProfile(@Body request: UpdateUserProfileRequestDto): Response<Unit>
}
