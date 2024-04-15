package com.micudasoftware.presentation.onboarding.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.micudasoftware.presentation.R

/**
 * A composable function that displays a top bar for the profile setup screen.
 * This top bar includes a title, a progress indicator, and a text showing the current progress.
 *
 * @param progress The current progress value.
 * @param maxProgress The maximum progress value.
 */
@Composable
fun SetupProfileTopBar(
    progress: Int,
    maxProgress: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title_setup_profile),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
        LinearProgressIndicator(
            modifier = Modifier.padding(top = 10.dp),
            progress = { progress.toFloat() / maxProgress.toFloat() }
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "$progress/$maxProgress",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.surfaceDim
        )
    }
}