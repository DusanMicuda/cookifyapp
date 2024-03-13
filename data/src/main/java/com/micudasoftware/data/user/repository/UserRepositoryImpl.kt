package com.micudasoftware.data.user.repository

import com.micudasoftware.data.user.api.dto.LoginRequestDto
import com.micudasoftware.data.user.api.dto.SignUpRequestDto
import com.micudasoftware.data.user.datasource.UserLocalDataSource
import com.micudasoftware.data.user.datasource.UserRemoteDataSource
import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.user.repository.UserRepository
import javax.inject.Inject

/**
 * The implementation of [UserRepository].
 *
 * @property userLocalDataSource The local data source for user data.
 * @property userRemoteDataSource The remote data source for user operations and data.
 * @see UserRepository
 */
class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override fun getAuthorizationToken(): String? =
        userLocalDataSource.getAuthorizationToken()

    override suspend fun login(email: String, password: String): Result<Unit> =
        userRemoteDataSource.login(LoginRequestDto(email, password))
            .onSuccess {
                userLocalDataSource.saveAuthorizationToken(it.token)
            }.map {}


    override suspend fun signup(name: String, email: String, password: String): Result<Unit> =
        userRemoteDataSource.signUp(SignUpRequestDto(name, email, password))

    override suspend fun authenticate(): Result<Unit> =
        userRemoteDataSource.authenticate()

}
