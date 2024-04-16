package com.micudasoftware.presentation.common.model

/**
 * Model for a button.
 *
 * @property text The text of the button.
 * @property onClick The action to be executed when the button is clicked.
 */
data class ButtonModel(
    val text: StringModel,
    val onClick: () -> Unit,
)
