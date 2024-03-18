package com.micudasoftware.data.userprofile.api

import com.micudasoftware.data.userprofile.api.dto.GetUserProfileRequestDto
import com.micudasoftware.data.userprofile.api.dto.GetUserProfileResponseDto
import com.micudasoftware.data.userprofile.api.dto.UpdateUserProfileRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Api for user profile operations.
 */
interface UserProfileApi {

    @GET("/profile")
    suspend fun getUserProfile(@Body request: GetUserProfileRequestDto): Response<GetUserProfileResponseDto>

    @POST("/profile")
    suspend fun updateUserProfile(@Body request: UpdateUserProfileRequestDto): Response<Unit>
}
