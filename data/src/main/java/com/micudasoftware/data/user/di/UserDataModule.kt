package com.micudasoftware.data.user.di

import android.content.SharedPreferences
import com.micudasoftware.data.user.api.UserApi
import com.micudasoftware.data.user.datasource.UserLocalDataSource
import com.micudasoftware.data.user.datasource.UserRemoteDataSource
import com.micudasoftware.data.user.repository.UserRepositoryImpl
import com.micudasoftware.domain.user.repository.UserRepository
import com.micudasoftware.domain.user.usecase.AutoLoginUseCase
import com.micudasoftware.domain.user.usecase.LoginUserUseCase
import com.micudasoftware.domain.user.usecase.SignUpAndLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    fun provideUserLocalDataSource(sharedPreferences: SharedPreferences) =
        UserLocalDataSource(sharedPreferences)

    @Provides
    fun provideUserRemoteDataSource(userApi: UserApi): UserRemoteDataSource =
        UserRemoteDataSource(userApi)

    @Provides
    fun provideUserRepository(
        userLocalDataSource: UserLocalDataSource,
        userRemoteDataSource: UserRemoteDataSource
    ): UserRepository = UserRepositoryImpl(userLocalDataSource, userRemoteDataSource)

    @Provides
    fun provideLoginUserUseCase(userRepository: UserRepository) = LoginUserUseCase(userRepository)

    @Provides
    fun provideSignUpAndLoginUseCase(userRepository: UserRepository) =
        SignUpAndLoginUseCase(userRepository)

    @Provides
    fun provideAutoLoginUseCase(userRepository: UserRepository) = AutoLoginUseCase(userRepository)

}
