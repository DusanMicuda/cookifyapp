package com.micudasoftware.presentation.feed.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.micudasoftware.presentation.common.padding
import com.micudasoftware.presentation.common.theme.PreviewTheme

@Composable
fun RatingLabel(
    rating: Float,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .then(
                Modifier.background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.small
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            imageVector = Icons.Outlined.Star,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp, end = 8.dp),
            text = rating.toString(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
private fun RatingLabelPreview() {
    PreviewTheme {
        RatingLabel(rating = 4.5f)
    }
}