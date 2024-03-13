package com.micudasoftware.data.user.datasource

import com.micudasoftware.data.common.ApiCaller
import com.micudasoftware.data.user.api.UserApi
import com.micudasoftware.data.user.api.dto.LoginRequestDto
import com.micudasoftware.data.user.api.dto.LoginResponseDto
import com.micudasoftware.data.user.api.dto.SignUpRequestDto
import com.micudasoftware.domain.common.Result
import javax.inject.Inject

/**
 * Remote data source for user operations.
 *
 * @property userApi The API for user operations.
 */
class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi
) {

    /**
     * Function to login user.
     *
     * @param dto The DTO for login request.
     * @return The [Result] with [LoginResponseDto].
     */
    suspend fun login(dto: LoginRequestDto): Result<LoginResponseDto> {
         return ApiCaller.callResult<LoginResponseDto>(
            apiCall = { userApi.login(dto) },
            errorMessage = { "Failed to login user!" }
        )
    }

    /**
     * Function to sign up new user.
     *
     * @param dto The DTO for sign up request.
     * @return The empty [Result].
     */
    suspend fun signUp(dto: SignUpRequestDto): Result<Unit> {
        return ApiCaller.callResult(
            apiCall = { userApi.signUp(dto) },
            errorMessage = { "Failed to sign up!" }
        )
    }

    /**
     * Function to authenticate user.
     *
     * @return The empty [Result].
     */
    suspend fun authenticate(): Result<Unit> {
        return ApiCaller.callResult(
            apiCall = { userApi.authenticate() },
            errorMessage = { "Failed to authenticate user!" }
        )
    }
}
