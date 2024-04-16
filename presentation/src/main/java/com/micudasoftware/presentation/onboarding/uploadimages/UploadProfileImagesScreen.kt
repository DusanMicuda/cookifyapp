package com.micudasoftware.presentation.onboarding.uploadimages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.micudasoftware.presentation.R
import com.micudasoftware.presentation.common.ComposeViewModel
import com.micudasoftware.presentation.common.PreviewViewModel
import com.micudasoftware.presentation.common.component.ScreenContentWrapper
import com.micudasoftware.presentation.common.padding
import com.micudasoftware.presentation.common.theme.PreviewTheme
import com.micudasoftware.presentation.common.utils.rememberBitmapFromUri
import com.micudasoftware.presentation.navigation.destinations.UpdateAboutMeScreenDestination
import com.micudasoftware.presentation.onboarding.common.component.SetupProfileTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

/**
 * Composable function representing the UploadProfileImagesScreen.
 * It allows the user to upload profile and title images.
 * It uses a ViewModel to handle events and update the view state.
 *
 * @param viewModel The ViewModel used to handle events and update the view state.
 * @param navigator The navigator used to navigate between screens.
 */
@Destination
@Composable
fun UploadProfileImagesScreen(
    viewModel: ComposeViewModel<SetupProfileViewState, UploadImagesEvent> = hiltViewModel<UploadImagesViewModel>(),
    navigator: DestinationsNavigator,
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    viewModel.registerNavObserver(navigator)

    val profileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let {
                viewModel.onEvent(UploadImagesEvent.UploadProfileImage(it))
            }
        }
    )
    val titleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let {
                viewModel.onEvent(UploadImagesEvent.UploadTitleImage(it))
            }
        }
    )

    ScreenContentWrapper(
        isLoading = viewState.isLoading,
        dialog = viewState.dialog
    ) {
        Scaffold(
            topBar = { SetupProfileTopBar(progress = 1, maxProgress = 2) }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 20.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.title_add_profile_image),
                    style = MaterialTheme.typography.titleMedium,
                )
                ImageUploadBox(
                    modifier = Modifier
                        .padding(top = 20.dp, horizontal = 20.dp)
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = { profileLauncher.launch("image/*") },
                    onDelete = { viewModel.onEvent(UploadImagesEvent.RemoveProfileImage) },
                    pictureState = viewState.profilePictureState
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = stringResource(id = R.string.title_add_background_image),
                    style = MaterialTheme.typography.titleMedium,
                )
                ImageUploadBox(
                    modifier = Modifier
                        .padding(top = 20.dp, horizontal = 10.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    onClick = { titleLauncher.launch("image/*") },
                    onDelete = { viewModel.onEvent(UploadImagesEvent.RemoveTitleImage) },
                    pictureState = viewState.titlePictureState,
                    shape = RoundedCornerShape(10.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { viewModel.onEvent(UploadImagesEvent.SaveUploadedImages) }
                    ) {
                        Text(text = stringResource(id = R.string.button_next))
                    }
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { navigator.navigate(UpdateAboutMeScreenDestination) }
                    ) {
                        Text(text = stringResource(id = R.string.button_skip))
                    }
                }

            }
        }
    }
}

/**
 * Composable function representing an image upload box.
 * It displays the image and the upload state, and allows the user to upload a new image or delete the current one.
 *
 * @param pictureState The state of the image upload.
 * @param onClick The function to call when the box is clicked.
 * @param onDelete The function to call when the delete button is clicked.
 * @param modifier The modifier to apply to the box.
 * @param shape The shape of the box.
 */
@Composable
private fun ImageUploadBox(
    pictureState: UploadPictureState,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = shape,
            ),
        contentAlignment = Alignment.Center
    ) {
        when (pictureState) {
            UploadPictureState.Idle -> {
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.12f),
                            shape = CircleShape
                        )
                        .clickable(onClick = onClick),
                ) {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            }

            UploadPictureState.Loading -> {
                CircularProgressIndicator()
            }

            is UploadPictureState.Done -> {
                rememberBitmapFromUri(uri = pictureState.uri)?.asImageBitmap()?.let {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        bitmap = it,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
                    )
                }
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp, end = 4.dp),
                    onClick = onDelete
                ) {
                    Surface(
                        shape = CircleShape,
                        shadowElevation = 6.dp,
                    ) {
                        Icon(
                            modifier = Modifier.padding(6.dp),
                            imageVector = Icons.Filled.Delete,
                            tint = MaterialTheme.colorScheme.error,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

/**
 * Composable function for previewing the UploadProfileImagesScreen.
 */
@Preview
@Composable
private fun UploadProfileImagesScreenPreview() {
    PreviewTheme {
        UploadProfileImagesScreen(
            viewModel = PreviewViewModel(
                SetupProfileViewState(
                    UploadPictureState.Loading,
                    UploadPictureState.Done(Uri.EMPTY)
                )
            ),
            navigator = EmptyDestinationsNavigator
        )
    }
}