package com.micudasoftware.presentation.onboarding.uploadimages

import com.micudasoftware.presentation.common.UIState

/**
 * Data class representing the view state of the SetupProfile screen.
 * It contains the state of the profile picture upload and the title picture upload.
 *
 * @property profilePictureState The state of the profile picture upload.
 * @property titlePictureState The state of the title picture upload.
 */
data class SetupProfileViewState(
    val profilePictureState: UploadPictureState = UploadPictureState.Idle,
    val titlePictureState: UploadPictureState = UploadPictureState.Idle,
) : UIState()

/**
 * Enum representing the different states of an image upload.
 */
enum class UploadPictureState {
    Idle,
    Loading,
    Done,
}