package com.micudasoftware.presentation.onboarding.uploadimages

import android.net.Uri
import com.micudasoftware.presentation.common.UIEvent

/**
 * Sealed class representing the different events that can occur in the UploadImages screen.
 * Each event is represented by a different subclass.
 */
sealed class UploadImagesEvent: UIEvent() {

    /**
     * Event representing the upload of a profile image.
     *
     * @property imageUri The Uri of the image to upload.
     */
    data class UploadProfileImage(val imageUri: Uri): UploadImagesEvent()

    /**
     * Event representing the upload of a title image.
     *
     * @property imageUri The Uri of the image to upload.
     */
    data class UploadTitleImage(val imageUri: Uri): UploadImagesEvent()

    /**
     * Event to save the uploaded images.
     */
    data object SaveUploadedImages: UploadImagesEvent()
}