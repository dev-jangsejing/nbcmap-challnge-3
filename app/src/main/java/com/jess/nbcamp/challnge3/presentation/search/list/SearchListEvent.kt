package com.jess.nbcamp.challnge3.presentation.search.list

sealed interface SearchListEvent {

    data class UpdateBookmark(
        val list: List<SearchListItem>,
    ) : SearchListEvent
}