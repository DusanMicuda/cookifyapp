package com.micudasoftware.presentation.onboarding.uploadimages

import android.net.Uri
import com.micudasoftware.presentation.common.UIState
import com.micudasoftware.presentation.common.model.DialogModel

/**
 * Data class representing the view state of the SetupProfile screen.
 * It contains the state of the profile picture upload and the title picture upload.
 *
 * @property profilePictureState The state of the profile picture upload.
 * @property titlePictureState The state of the title picture upload.
 * @property isLoading The flag that indicates if loading animation should be shown.
 * @property dialog The dialog to be shown.
 */
data class SetupProfileViewState(
    val profilePictureState: UploadPictureState = UploadPictureState.Idle,
    val titlePictureState: UploadPictureState = UploadPictureState.Idle,
    val isLoading: Boolean = false,
    val dialog: DialogModel? = null,
) : UIState()

/**
 * Sealed representing the different states of an image upload.
 */
sealed class UploadPictureState {
    /**
     * The initial state when no upload is in progress.
     */
    data object Idle: UploadPictureState()

    /**
     * The state when an image is being uploaded.
     */
    data object Loading: UploadPictureState()

    /**
     * The state when an image has been successfully uploaded.
     *
     * @param uri The Uri of the uploaded image.
     */
    data class Done(val uri: Uri): UploadPictureState()
}