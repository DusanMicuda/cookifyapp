package com.micudasoftware.presentation.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import timber.log.Timber

/**
 * Extension function on Uri to get a Bitmap representation of the Uri.
 * It uses the ImageDecoder for Android P and above, and MediaStore for older versions.
 * If the bitmap retrieval fails, it logs the exception and returns null.
 *
 * @param context The context used to access the content resolver.
 * @return The Bitmap representation of the Uri, or null if the retrieval fails.
 */
@Suppress("DEPRECATION")
fun Uri.getBitmapOrNull(
    context: Context,
): Bitmap? =
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, this)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, this)
        }
    } catch (e: Exception) {
        Timber.e(e, "Failed to get bitmap!")
        null
    }

/**
 * Composable function to remember a Bitmap from a Uri.
 * It uses the LocalContext to get the current context and the getBitmapOrNull extension function on Uri.
 *
 * @param uri The Uri of the image to remember.
 * @return The remembered Bitmap, or null if the retrieval fails.
 */
@Composable
fun rememberBitmapFromUri(uri: Uri): Bitmap? {
    val context = LocalContext.current
    return remember {
        uri.getBitmapOrNull(context = context)
    }
}