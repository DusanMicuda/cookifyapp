package com.micudasoftware.presentation.common.model
import com.ramcosta.composedestinations.spec.Direction

/**
 * A sealed class representing navigation events in the application.
 * This class has three subclasses representing different types of navigation events.
 */
sealed class NavEvent {

    /**
     * Represents a navigation event to a specific direction.
     * @property direction The direction to navigate to.
     */
    data class To(val direction: Direction) : NavEvent()

    /**
     * Represents a navigation event to go back in the navigation stack.
     */
    data object Back : NavEvent()

    /**
     * Represents a navigation event to go back to a specific direction in the navigation stack.
     * @property direction The direction to navigate back to.
     * @property inclusive Whether the navigation back is inclusive of the direction or not.
     */
    data class BackTo(val direction: Direction, val inclusive: Boolean = false) : NavEvent()
}