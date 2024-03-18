package com.micudasoftware.data.userprofile.di

import com.micudasoftware.data.userprofile.api.UserProfileApi
import com.micudasoftware.data.userprofile.datasource.UserProfileDataSource
import com.micudasoftware.data.userprofile.repository.UserProfileRepositoryImpl
import com.micudasoftware.domain.userprofile.repository.UserProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UserProfileDataModule {

    @Provides
    fun provideUserProfileApi(retrofit: Retrofit): UserProfileApi =
        retrofit.create(UserProfileApi::class.java)

    @Provides
    fun provideUserProfileDataSource(userProfileApi: UserProfileApi) =
        UserProfileDataSource(userProfileApi)

    @Provides
    fun provideUserProfileRepository(userProfileDataSource: UserProfileDataSource): UserProfileRepository =
        UserProfileRepositoryImpl(userProfileDataSource)

}