package com.micudasoftware.presentation.common.model

import androidx.annotation.StringRes

/**
 * Model for text field with error message.
 *
 * @property value The text field value.
 * @property error The error message.
 */
data class ValidatedFieldModel(
    val value: String = "",
    @StringRes val error: Int? = null,
) {
    /**
     * Function to check if there is an error.
     */
    fun isError() = error != null

    /**
     * Function to check if there is not an error.
     */
    fun isNotError() = error == null
}
