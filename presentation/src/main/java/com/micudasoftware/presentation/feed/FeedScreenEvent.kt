package com.micudasoftware.presentation.feed

import com.micudasoftware.presentation.common.UIEvent

/**
 * TODO CLASS_DESCRIPTION
 */
sealed class FeedScreenEvent: UIEvent() {
    data object LoadMoreRecipes: FeedScreenEvent()
}
