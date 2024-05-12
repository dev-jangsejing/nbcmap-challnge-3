package com.jess.nbcamp.challenge3.presentation.search.shared

import com.jess.nbcamp.challenge3.presentation.search.bookmark.BookmarkListItem

sealed interface SearchSharedEvent {

    data class UpdateBookmark(
        val list: List<BookmarkListItem>,
    ) : SearchSharedEvent
}