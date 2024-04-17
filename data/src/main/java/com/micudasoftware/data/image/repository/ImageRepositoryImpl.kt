package com.micudasoftware.data.image.repository

import android.net.Uri
import com.micudasoftware.data.image.datasource.ImageLocalDataSource
import com.micudasoftware.data.image.datasource.ImageRemoteDataSource
import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.image.model.FileData
import com.micudasoftware.domain.image.repository.ImageRepository
import com.micudasoftware.domain.image.repository.ImageUrl

/**
 * Implementation of [ImageRepository].
 *
 * @property localDataSource The data source for local image data.
 * @property remoteDataSource The data source for remote image operations.
 */
class ImageRepositoryImpl(
    private val localDataSource: ImageLocalDataSource,
    private val remoteDataSource: ImageRemoteDataSource,
): ImageRepository {

    override suspend fun getFileDataFromUri(uri: String): Result<FileData> =
        localDataSource.getFileDataFromUri(Uri.parse(uri))

    override suspend fun uploadImage(fileData: FileData): Result<ImageUrl> =
        remoteDataSource.uploadImage(fileData).map { it.fileUrl }
}
