package com.micudasoftware.domain.image.usecase

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.image.repository.ImageRepository
import com.micudasoftware.domain.image.repository.ImageUrl

/**
 * Use case for uploading image.
 *
 * @property imageRepository The repository for image operations.
 */
class UploadImageUseCase(
    private val imageRepository: ImageRepository,
) {

    /**
     * Function to upload image.
     *
     * @param uri The android content Uri of file.
     * @return The [Result] with url of the uploaded image.
     */
    suspend operator fun invoke(uri: String): Result<ImageUrl> =
        imageRepository.getFileDataFromUri(uri).chain {
            imageRepository.uploadImage(it)
        }
}
