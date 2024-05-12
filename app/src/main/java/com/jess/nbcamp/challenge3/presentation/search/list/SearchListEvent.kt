package com.jess.nbcamp.challenge3.presentation.search.list

sealed interface SearchListEvent {

    data class UpdateBookmark(
        val list: List<SearchListItem>,
    ) : SearchListEvent
}