package com.micudasoftware.data.image.datasource

import android.net.Uri
import java.io.File

/**
 * Data class representing data for file to upload.
 *
 * @property file The copied file to upload.
 * @property mimeType The mime type of a file.
 * @property uri The android content uri to original file.
 */
data class FileData(
    val file: File,
    val mimeType: String,
    val uri: Uri
)
