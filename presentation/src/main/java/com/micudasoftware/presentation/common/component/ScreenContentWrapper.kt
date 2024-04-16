package com.micudasoftware.presentation.common.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.micudasoftware.presentation.common.model.ButtonModel
import com.micudasoftware.presentation.common.model.DialogModel
import com.micudasoftware.presentation.common.model.StringModel
import com.micudasoftware.presentation.common.padding
import com.micudasoftware.presentation.common.theme.PreviewTheme

/**
 * A composable function that wraps the content of a screen.
 * It provides functionality for showing a loading state and a dialog.
 *
 * @param isLoading A boolean indicating whether the loading state should be shown.
 * @param dialog An optional DialogModel that describes the dialog to be shown.
 * @param content A @Composable function that describes the content of the screen.
 */
@Composable
fun ScreenContentWrapper(
    isLoading: Boolean = false,
    dialog: DialogModel? = null,
    content: @Composable () -> Unit,
) {
    content()
    AnimatedVisibility(visible = dialog != null) {
        dialog?.let { Dialog(dialog = it) }
    }
    AnimatedVisibility(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)),
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Loading()
    }
}

/**
 * Composable function that shows a dialog.
 *
 * @param dialog A DialogModel that describes the dialog to be shown.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Dialog(dialog: DialogModel) {
    BasicAlertDialog(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.background,
            shape = MaterialTheme.shapes.medium
        ),
        properties = DialogProperties(),
        onDismissRequest = {},
    ) {
        Column(
            modifier = Modifier.padding(top = 16.dp, bottom = 10.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = dialog.title.getString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = dialog.message.getString(),
                style = MaterialTheme.typography.bodyMedium,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                dialog.negativeButton?.let {
                    TextButton(onClick = it.onClick) {
                        Text(text = it.text.getString())
                    }
                }
                TextButton(onClick = dialog.positiveButton.onClick) {
                    Text(text = dialog.positiveButton.text.getString())
                }
            }
        }
    }
}

/**
 * Composable function that shows a loading state.
 */
@Composable
private fun Loading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = false) {},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Loading...",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Preview for the ScreenContentWrapper with a loading state.
 */
@Preview
@Composable
private fun ScreenContentWrapperLoadingPreview() {
    PreviewTheme {
        ScreenContentWrapper(isLoading = true) {}
    }
}

/**
 * Preview for the ScreenContentWrapper with a dialog.
 */
@Preview
@Composable
private fun ScreenContentWrapperDialogPreview() {
    PreviewTheme {
        ScreenContentWrapper(
            dialog = DialogModel(
                title = StringModel.String("Title"),
                message = StringModel.String("Message"),
                positiveButton = ButtonModel(StringModel.String("Positive")) {},
                negativeButton = ButtonModel(StringModel.String("Negative")) {},
                onDismiss = {},
            ),
        ) {}
    }
}