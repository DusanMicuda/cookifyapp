package com.micudasoftware.presentation.feed.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.micudasoftware.presentation.common.theme.PreviewTheme
import com.micudasoftware.presentation.feed.model.AuthorModel
import com.micudasoftware.presentation.feed.model.RecipeItemModel

@Composable
fun RecipeItem(
    model: RecipeItemModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f/9f),
                model = "http://192.168.9.179:8080/image/" + model.imageUrl,
                contentDescription = model.title,
            )
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(32.dp)
                                .padding(end = 8.dp)
                                .aspectRatio(1f)
                                .align(Alignment.CenterVertically)
                                .clip(RoundedCornerShape(50.dp)),
                            model = "http://192.168.9.179:8080/image/" + model.authorModel.profileImageUrl,
                            contentDescription = model.authorModel.name,
                        )
                        Text(
                            text = model.authorModel.name,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    RatingLabel(
                        rating = model.rating,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecipeItemPreview() {
    PreviewTheme {
        RecipeItem(
            model = RecipeItemModel(
                title = "Title",
                imageUrl = "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg",
                authorModel = AuthorModel(
                    name = "Author",
                    profileImageUrl = "https://via.placeholder.com/150"
                ),
                rating = 4.5f
            ),
            onClick = {}
        )
    }
}