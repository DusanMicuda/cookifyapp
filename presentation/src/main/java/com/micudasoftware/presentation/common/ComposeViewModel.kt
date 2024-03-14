package com.micudasoftware.presentation.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface to wrap ViewModel for easier work with compose screens and previews.
 *
 * @param D The [UIState] that contains all the necessary data for screen.
 * @param T The [UIEvent] that contains all the events that can occur on screen.
 */
interface ComposeViewModel<D: UIState, T: UIEvent> {

    /**
     * Property to access [UIState] form screen.
     */
    val viewState: StateFlow<D>

    /**
     * Function to handle events occurred on screen.
     *
     * @param event The occurred event.
     */
    fun onEvent(event: T)
}

/**
 * Helper class to easily pass the [UIState] into preview of screen.
 *
 * @param data The [UIState] for preview.
 * @see ComposeViewModel
 */
class PreviewViewModel<D: UIState, T: UIEvent>(data: D) : ComposeViewModel<D, T> {

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