package com.micudasoftware.presentation.onboarding.updateaboutme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.micudasoftware.presentation.R
import com.micudasoftware.presentation.common.ComposeViewModel
import com.micudasoftware.presentation.common.PreviewViewModel
import com.micudasoftware.presentation.common.theme.PreviewTheme
import com.micudasoftware.presentation.onboarding.common.component.SetupProfileTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

/**
 * Composable function for the UpdateAboutMeScreen.
 * This screen allows the user to update their "About Me" information.
 *
 * @param viewModel ViewModel that handles the business logic of this screen.
 * @param navigator Navigator for handling navigation events.
 */
@Destination
@Composable
fun UpdateAboutMeScreen(
    viewModel: ComposeViewModel<UpdateAboutMeViewState, UpdateAboutMeEvent> = hiltViewModel<UpdateAboutMeViewModel>(),
    navigator: DestinationsNavigator,
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    viewModel.registerNavObserver(navigator)

    var aboutMe by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = { SetupProfileTopBar(progress = 2, maxProgress = 2) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.title_about_me),
                style = MaterialTheme.typography.titleMedium,
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                value = aboutMe,
                minLines = 5,
                placeholder = { Text(text = stringResource(id = R.string.placeholder_about_me)) },
                onValueChange = { aboutMe = it },
                shape = RoundedCornerShape(10.dp),
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
                    onClick = { viewModel.onEvent(UpdateAboutMeEvent.UpdateAboutMe(aboutMe)) }
                ) {
                    Text(text = stringResource(id = R.string.button_finish))
                }
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onEvent(UpdateAboutMeEvent.Skip) }
                ) {
                    Text(text = stringResource(id = R.string.button_skip))
                }
            }
        }
    }
}

/**
 * Preview for the UpdateAboutMeScreen.
 */
@Preview
@Composable
private fun UpdateAboutMeScreenPreview() {
    PreviewTheme {
        UpdateAboutMeScreen(
            PreviewViewModel(UpdateAboutMeViewState()),
            navigator = EmptyDestinationsNavigator
        )
    }
}