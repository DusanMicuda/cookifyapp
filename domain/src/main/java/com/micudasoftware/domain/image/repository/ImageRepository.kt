package com.micudasoftware.domain.image.repository

import com.micudasoftware.domain.common.Result

/**
 * Repository for image operations and data.
 */
interface ImageRepository {

    /**
     * Function to upload image.
     *
     * @param uri The android content Uri of file to upload.
     * @return The [Result] with url of the uploaded image.
     */
    suspend fun uploadImage(uri: String): Result<ImageUrl>

}

/**
 * The url of uploaded image.
 */
typealias ImageUrl = String
