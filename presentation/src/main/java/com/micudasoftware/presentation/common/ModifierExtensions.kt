package com.micudasoftware.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Modifier extension to to easily combine padding parameters.
 *
 * @param top Top padding.
 * @param bottom Bottom padding.
 * @param start Start padding.
 * @param end End padding.
 * @param horizontal Horizontal padding.
 * @param vertical Vertical padding.
 */
fun Modifier.padding(
    top: Dp = 0.dp,
    bottom: Dp = 0.dp,
    start: Dp = 0.dp,
    end: Dp = 0.dp,
    horizontal: Dp = 0.dp,
    vertical: Dp = 0.dp
) = this.then(
    Modifier.padding(
        top = maxOf(top, vertical).coerceAtLeast(0.dp),
        bottom = maxOf(bottom, vertical).coerceAtLeast(0.dp),
        start = maxOf(start, horizontal).coerceAtLeast(0.dp),
        end = maxOf(end, horizontal).coerceAtLeast(0.dp)
    )
)