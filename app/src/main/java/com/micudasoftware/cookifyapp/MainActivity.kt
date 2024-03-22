package com.micudasoftware.cookifyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.micudasoftware.presentation.common.theme.CookifyAppTheme
import com.micudasoftware.presentation.navigation.NavGraphs
import com.micudasoftware.presentation.navigation.destinations.LoginSignUpScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var startScreen = LoginSignUpScreenDestination
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isLoading.value }
            setOnExitAnimationListener {
                if (viewModel.isLoggedIn.value) {
                    // Todo set start screen depends on result of autologin
                }
                it.remove()
            }
        }
        setContent {
            CookifyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        startRoute = startScreen
                    )
                }
            }
        }
    }
}
