package com.micudasoftware.presentation.common.model

/**
 * Model for a dialog.
 *
 * @property title The title of the dialog.
 * @property message The message of the dialog.
 * @property positiveButton The positive button of the dialog.
 * @property negativeButton The negative button of the dialog.
 * @property onDismiss The action to be executed when the dialog is dismissed.
 */
data class DialogModel(
    val title: StringModel,
    val message: StringModel,
    val positiveButton: ButtonModel,
    val negativeButton: ButtonModel? = null,
    val onDismiss: () -> Unit,
)
