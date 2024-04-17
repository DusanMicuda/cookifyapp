package com.micudasoftware.domain.image.model

import java.io.File

/**
 * Data class representing data for file to upload.
 *
 * @property file The copied file to upload.
 * @property mimeType The mime type of a file.
 */
data class FileData(
    val file: File,
    val mimeType: String,
)
