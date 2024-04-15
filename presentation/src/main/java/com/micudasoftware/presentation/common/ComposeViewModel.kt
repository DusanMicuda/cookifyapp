package com.micudasoftware.presentation.common

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micudasoftware.presentation.common.model.NavEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Interface to wrap ViewModel for easier work with compose screens and previews.
 *
 * @param D The [UIState] that contains all the necessary data for screen.
 * @param T The [UIEvent] that contains all the events that can occur on screen.
 */
abstract class ComposeViewModel<D: UIState, T: UIEvent>: ViewModel() {

    /**
     * Property to access [UIState] form screen.
     */
    abstract val viewState: StateFlow<D>

    /**
     * Channel to send navigation events to screen.
     */
    private val _navEvent = Channel<NavEvent>(
        capacity = Channel.CONFLATED,
        onUndeliveredElement = {
            Timber.d("Undelivered navigation event: $it")
        }
    )

    /**
     * Flow to observe navigation events.
     */
    private val navEvent: Flow<NavEvent> = _navEvent.receiveAsFlow()

    /**
     * Function to handle events occurred on screen.
     *
     * @param event The occurred event.
     */
    abstract fun onEvent(event: T)

    /**
     * Function to send navigation event to screen.
     *
     * @param navEvent The navigation event to send.
     */
    fun navigate(navEvent: NavEvent) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _navEvent.send(navEvent)
        }
    }

    /**
     * Function to register navigation observer to screen.
     *
     * IMPORTANT: This function should be called in just one screen composable, otherwise
     * it can lead to navigation issues.
     *
     * @param navigator The [DestinationsNavigator] to navigate.
     */
    @SuppressLint("ComposableNaming")
    @Composable
    fun registerNavObserver(navigator: DestinationsNavigator) {
        navEvent.collectWithLifecycle(
            lifecycleOwner = LocalLifecycleOwner.current,
            minActiveState = Lifecycle.State.RESUMED
        ) { event ->
            Timber.d("Received navigation event: $event")
            when (event) {
                is NavEvent.To -> navigator.navigate(event.direction)
                is NavEvent.Back -> navigator.navigateUp()
                is NavEvent.BackTo -> navigator.popBackStack(event.direction.route, event.inclusive)
            }
        }
    }
}

/**
 * Helper class to easily pass the [UIState] into preview of screen.
 *
 * @param data The [UIState] for preview.
 * @see ComposeViewModel
 */
class PreviewViewModel<D: UIState, T: UIEvent>(data: D) : ComposeViewModel<D, T>() {

    override val viewState: StateFlow<D> = MutableStateFlow(data)

    override fun onEvent(event: T) { /* Empty */ }
}

/**
 * Helper class to define sealed classes for ui events.
 */
open class UIEvent

/**
 * Helper class to define data classes for ui states.
 */
open class UIState