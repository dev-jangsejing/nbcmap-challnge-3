package com.jess.nbcamp.challnge3.presentation.search.bookmark

data class BookmarkListUiState(
    val list: List<BookmarkListItem>,
    val isLoading: Boolean
) {

    companion object {

        fun init() = BookmarkListUiState(
            list = emptyList(),
            isLoading = false
        )
    }
}