package com.jess.nbcamp.challnge3.presentation.search.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.nbcamp.challnge3.presentation.search.bookmark.BookmarkListItem
import com.jess.nbcamp.challnge3.presentation.search.list.SearchListItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchSharedViewModel : ViewModel() {

    private val _event = MutableSharedFlow<SearchSharedEvent>()
    val event: SharedFlow<SearchSharedEvent> = _event.asSharedFlow()

    fun updateBookmarkItems(list: List<SearchListItem>) = viewModelScope.launch {
        list.filter {
            it.bookmarked
        }
            .map {
                BookmarkListItem(
                    id = it.id,
                    title = it.title,
                    bookmarked = it.bookmarked
                )
            }.also {
                _event.emit(SearchSharedEvent.UpdateBookmark(it))
            }
    }

}