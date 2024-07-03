package com.micudasoftware.presentation.feed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.micudasoftware.presentation.common.ComposeViewModel
import com.micudasoftware.presentation.common.PreviewViewModel
import com.micudasoftware.presentation.common.component.ScreenContentWrapper
import com.micudasoftware.presentation.common.reachedBottom
import com.micudasoftware.presentation.common.theme.PreviewTheme
import com.micudasoftware.presentation.feed.component.RecipeItem
import com.micudasoftware.presentation.feed.model.AuthorModel
import com.micudasoftware.presentation.feed.model.RecipeItemModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun FeedScreen(
    viewModel: ComposeViewModel<FeedScreenViewState, FeedScreenEvent> = hiltViewModel<FeedViewModel>(),
    navigator: DestinationsNavigator,
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    viewModel.registerNavObserver(navigator)

    ScreenContentWrapper() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(0.8f),
                        text = "What are we going to cook today?",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(48.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(50.dp)),
                        model = "http://192.168.9.179:8080/image/" + viewState.profileImageUrl,
                        contentDescription = null,
                    )
                }
            }
        ) { paddingValues ->
            val listState = rememberLazyListState()
            val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }
            LaunchedEffect(reachedBottom) {
                if (reachedBottom && !viewState.bottomLoading)
                    viewModel.onEvent(FeedScreenEvent.LoadMoreRecipes)
            }
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(10.dp),
                state = listState,
            ) {
                items(viewState.recipes) { recipe ->
                    RecipeItem(
                        modifier = Modifier.padding(bottom = 16.dp),
                        model = recipe,
                        onClick = { /* TODO: Navigate to recipe details */ }
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        AnimatedVisibility(
                            modifier = Modifier.fillMaxWidth(),
                            visible = viewState.bottomLoading
                        ) {
                            CircularProgressIndicator()
                        }
                        Text(text = "You have seen all the recipes!");
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FeedScreenPreview() {
    PreviewTheme {
        FeedScreen(
            viewModel = PreviewViewModel(
                FeedScreenViewState(
                    recipes = listOf(
                        RecipeItemModel(
                            imageUrl = "https://www.example.com/image.jpg",
                            title = "Recipe title",
                            authorModel = AuthorModel(
                                profileImageUrl = "https://www.example.com/profile.jpg",
                                name = "Author name"
                            ),
                            rating = 4.5f
                        ),
                        RecipeItemModel(
                            imageUrl = "https://www.example.com/image.jpg",
                            title = "Recipe title",
                            authorModel = AuthorModel(
                                profileImageUrl = "https://www.example.com/profile.jpg",
                                name = "Author name"
                            ),
                            rating = 4.5f
                        ),
                    ),
                )
            ),
            navigator = EmptyDestinationsNavigator
        )
    }
}
