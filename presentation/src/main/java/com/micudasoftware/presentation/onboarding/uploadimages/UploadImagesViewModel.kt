package com.micudasoftware.presentation.onboarding.uploadimages

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micudasoftware.domain.image.repository.ImageRepository
import com.micudasoftware.presentation.common.ComposeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for the UploadProfileImagesScreen.
 * It handles events from the UI and updates the view state accordingly.
 * It uses the ImageRepository to upload images.
 *
 * @property imageRepository The repository used to upload images.
 */
@HiltViewModel
class UploadImagesViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
): ViewModel(), ComposeViewModel<SetupProfileViewState, UploadImagesEvent> {

    // The current view state, represented as a MutableStateFlow. This allows the UI to observe changes to the state.
    private val _viewState = MutableStateFlow(SetupProfileViewState())
    // Expose an immutable view of the state to the UI.
    override val viewState = _viewState.asStateFlow()

    /**
     * Handles events from the UI.
     * It updates the view state and triggers side effects (like image uploads) based on the event.
     *
     * @param event The event to handle.
     */
    override fun onEvent(event: UploadImagesEvent) {
        when (event) {
            is UploadImagesEvent.UploadProfileImage -> uploadProfileImage(event.imageUri)
            is UploadImagesEvent.UploadTitleImage -> uploadTitleImage(event.imageUri)
            UploadImagesEvent.SaveUploadedImages -> saveUploadedImages()
        }
    }

    /**
     * Uploads a profile image.
     * It updates the view state to Loading, then uploads the image.
     * If the upload is successful, it updates the view state to Done.
     * If the upload fails, it logs the error.
     *
     * @param uri The Uri of the image to upload.
     */
    private fun uploadProfileImage(uri: Uri) {
        _viewState.update { it.copy(profilePictureState = UploadPictureState.Loading) }
        viewModelScope.launch {
            imageRepository.uploadImage(uri.toString())
                .onSuccess {
                    _viewState.update { it.copy(titlePictureState = UploadPictureState.Done) }
                }.onError {
                    Timber.e(it.throwable, "${it.code} - ${it.message}")
                }
        }
    }

    /**
     * Uploads a title image.
     * It updates the view state to Loading, then uploads the image.
     * If the upload is successful, it updates the view state to Done.
     * If the upload fails, it logs the error.
     *
     * @param uri The Uri of the image to upload.
     */
    private fun uploadTitleImage(uri: Uri) {
        _viewState.update { it.copy(titlePictureState = UploadPictureState.Loading) }
        viewModelScope.launch {
            imageRepository.uploadImage(uri.toString())
                .onSuccess {
                    _viewState.update { it.copy(titlePictureState = UploadPictureState.Done) }
                }.onError {
                    Timber.e(it.throwable, "${it.code} - ${it.message}")
                }
        }
    }

    private fun saveUploadedImages() {
        // Todo implement
    }
}