package com.micudasoftware.data.image.datasource

import com.micudasoftware.data.common.ApiCaller
import com.micudasoftware.data.image.api.ImageApi
import com.micudasoftware.data.image.api.dto.UploadImageResponseDto
import com.micudasoftware.domain.common.Result
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

/**
 * Remote data source for image operations.
 *
 * @property imageApi The API for image operations.
 */
class ImageRemoteDataSource(
    private val imageApi: ImageApi,
) {

    /**
     * Function to upload image.
     *
     * @param fileData The [FileData] of file to upload.
     * @return The [Result] with [UploadImageResponseDto].
     */
    suspend fun uploadImage(fileData: FileData): Result<UploadImageResponseDto> {
        return ApiCaller.callResult<UploadImageResponseDto>(
            apiCall = {
                val body = MultipartBody.Part.createFormData(
                    fileData.file.name,
                    fileData.file.name,
                    fileData.file.asRequestBody(fileData.mimeType.toMediaType())
                )
                imageApi.uploadImage(body)
            },
            errorMessage = { "Failed to upload image!" }
        )
    }

}
