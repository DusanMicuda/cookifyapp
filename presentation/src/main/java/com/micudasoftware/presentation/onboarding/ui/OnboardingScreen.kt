package com.micudasoftware.presentation.onboarding.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.micudasoftware.presentation.R
import com.micudasoftware.presentation.common.ComposeViewModel
import com.micudasoftware.presentation.common.PreviewViewModel
import com.micudasoftware.presentation.common.component.ValidatedTextField
import com.micudasoftware.presentation.common.padding
import com.micudasoftware.presentation.common.theme.PreviewTheme
import com.micudasoftware.presentation.onboarding.OnboardingEvent
import com.micudasoftware.presentation.onboarding.OnboardingScreenState
import com.micudasoftware.presentation.onboarding.OnboardingViewModel
import com.micudasoftware.presentation.onboarding.OnboardingViewState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

/**
 * Screen for login or register user.
 *
 * @param viewModel The [OnboardingViewModel] represented as [ComposeViewModel].
 * @param navigator The [DestinationsNavigator].
 */
@RootNavGraph(start = true)
@Destination
@Composable
fun OnboardingScreen(
    viewModel: ComposeViewModel<OnboardingViewState, OnboardingEvent> = hiltViewModel<OnboardingViewModel>(),
    navigator: DestinationsNavigator,
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, top = 46.dp),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, top = 16.dp),
            text = stringResource(id = R.string.app_description),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Image(
            modifier = Modifier
                .padding(horizontal = 56.dp, top = 42.dp)
                .weight(1f),
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = null,
        )
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = viewState.screenState == OnboardingScreenState.SignUp,
                enter = slideInVertically(),
                exit = slideOutVertically(),
            ) {
                ValidatedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.label_full_name),
                    value = viewState.name.value,
                    error = viewState.name.error?.let { stringResource(it) },
                    singleLine = true,
                    onValueChange = { viewModel.onEvent(OnboardingEvent.ChangeName(it)) },
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                )
            }
            ValidatedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                label = stringResource(id = R.string.label_email),
                value = viewState.email.value,
                error = viewState.email.error?.let { stringResource(it) },
                singleLine = true,
                onValueChange = { viewModel.onEvent(OnboardingEvent.ChangeEmail(it)) },
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            ValidatedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                label = stringResource(id = R.string.label_password),
                value = viewState.password.value,
                error = viewState.password.error?.let { stringResource(it) },
                singleLine = true,
                onValueChange = { viewModel.onEvent(OnboardingEvent.ChangePassword(it)) },
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                onClick = {
                    viewModel.onEvent(
                        when(viewState.screenState) {
                            OnboardingScreenState.Login -> OnboardingEvent.Login
                            OnboardingScreenState.SignUp -> OnboardingEvent.SignUp
                        }
                    )
                }
            ) {
                Text(
                    text = stringResource(
                        when(viewState.screenState) {
                            OnboardingScreenState.Login -> R.string.button_login
                            OnboardingScreenState.SignUp -> R.string.button_signup
                        }
                    )
                )
            }
            Row(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = stringResource(
                        when (viewState.screenState) {
                            OnboardingScreenState.Login -> R.string.text_dont_have_account
                            OnboardingScreenState.SignUp -> R.string.text_already_have_account
                        }
                    ),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .clickable {
                            viewModel.onEvent(
                                when (viewState.screenState) {
                                    OnboardingScreenState.Login -> OnboardingEvent.SwitchToSignUp
                                    OnboardingScreenState.SignUp -> OnboardingEvent.SwitchToLogin
                                }
                            )
                        },
                    text = stringResource(
                        when (viewState.screenState) {
                            OnboardingScreenState.Login -> R.string.text_create_account
                            OnboardingScreenState.SignUp -> R.string.text_login
                        }
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

/**
 * Preview for [OnboardingScreen] in login state.
 */
@Preview(device = "id:pixel")
@Preview
@Composable
private fun OnboardingLoginScreenPreview() {
    PreviewTheme {
        OnboardingScreen(
            viewModel = PreviewViewModel(OnboardingViewState()),
            navigator = EmptyDestinationsNavigator
        )
    }
}

/**
 * Preview for [OnboardingScreen] in sign up state.
 */
@Preview(device = "id:pixel")
@Preview
@Composable
private fun OnboardingSignUpScreenPreview() {
    PreviewTheme {
        OnboardingScreen(
            viewModel = PreviewViewModel(
                OnboardingViewState(screenState = OnboardingScreenState.SignUp)
            ),
            navigator = EmptyDestinationsNavigator
        )
    }
}
