package com.micudasoftware.domain.image.repository

import com.micudasoftware.domain.common.Result
import com.micudasoftware.domain.image.model.FileData

/**
 * Repository for image operations and data.
 */
interface ImageRepository {

    /**
     * Function to get file data from Uri.
     *
     * @param uri The android content Uri of file.
     * @return The [Result] with file data.
     */
    suspend fun getFileDataFromUri(uri: String): Result<FileData>

    /**
     * Function to upload image.
     *
     * @param fileData The [FileData] of file to upload.
     * @return The [Result] with url of the uploaded image.
     */
    suspend fun uploadImage(fileData: FileData): Result<ImageUrl>

}

/**
 * The url of uploaded image.
 */
typealias ImageUrl = String
