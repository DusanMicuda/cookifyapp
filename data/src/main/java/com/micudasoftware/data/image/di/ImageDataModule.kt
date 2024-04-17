package com.micudasoftware.data.image.di

import android.content.Context
import com.micudasoftware.data.image.api.ImageApi
import com.micudasoftware.data.image.datasource.ImageLocalDataSource
import com.micudasoftware.data.image.datasource.ImageRemoteDataSource
import com.micudasoftware.data.image.repository.ImageRepositoryImpl
import com.micudasoftware.domain.image.repository.ImageRepository
import com.micudasoftware.domain.image.usecase.UploadImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ImageDataModule {

    @Provides
    fun provideImageApi(retrofit: Retrofit): ImageApi =
        retrofit.create(ImageApi::class.java)

    @Provides
    fun provideImageLocalDataSource(@ApplicationContext context: Context) =
        ImageLocalDataSource(context)

    @Provides
    fun provideImageDataSource(imageApi: ImageApi,) =
        ImageRemoteDataSource(imageApi)

    @Provides
    fun provideImageRepository(
        localDataSource: ImageLocalDataSource,
        remoteDataSource: ImageRemoteDataSource,
    ): ImageRepository = ImageRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    fun provideUploadImageUseCase(
        imageRepository: ImageRepository
    ) = UploadImageUseCase(imageRepository)
}