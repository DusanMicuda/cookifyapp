package com.micudasoftware.data.image.datasource

import android.content.Context
import android.net.Uri
import com.micudasoftware.domain.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID

/**
 * Local data source for image data.
 *
 * @property context The Application context.
 */
class ImageLocalDataSource(
    private val context: Context,
) {

    /**
     * Function to get [FileData] from android content [Uri].
     *
     * @param uri The Uri of original file.
     * @return The [Result] with [FileData] for given [Uri].
     */
    suspend fun getFileDataFromUri(uri: Uri): Result<FileData> =
        withContext(Dispatchers.IO) {
            val fileExtension = uri.path?.substringAfterLast('.', "")
            if (fileExtension.isNullOrBlank()) {
                return@withContext Result.Error(message = "File extension is null or blank!")
            }

            val mimeType = context.contentResolver.getType(uri)
            if (mimeType.isNullOrBlank()) {
                return@withContext Result.Error(message = "File mime type is null or blank!")
            }

            val file = File(context.cacheDir, "${UUID.randomUUID()}.$fileExtension")
            try {
                file.createNewFile()
                file.outputStream().use {
                    context.contentResolver.openInputStream(uri)?.copyTo(it)
                }
            } catch (e: Exception) {
                return@withContext Result.Error(message = "Failed to copy file: ${e.message}", throwable = e)
            }


            Result.Success(
                FileData(
                    file = file,
                    mimeType = mimeType,
                    uri = uri
                )
            )
        }

}
