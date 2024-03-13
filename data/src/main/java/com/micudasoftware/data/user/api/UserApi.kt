package com.micudasoftware.data.user.api

import com.micudasoftware.data.user.api.dto.LoginRequestDto
import com.micudasoftware.data.user.api.dto.LoginResponseDto
import com.micudasoftware.data.user.api.dto.SignUpRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * The user API.
 */
interface UserApi {

    @POST("/login")
    suspend fun login(@Body dto: LoginRequestDto): Response<LoginResponseDto>

    @POST("/signup")
    suspend fun signUp(@Body dto: SignUpRequestDto): Response<Unit>

    @GET("/authenticate")
    suspend fun authenticate(): Response<Unit>
}
