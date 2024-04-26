package com.jess.nbcamp.challnge3.presentation.search.bookmark

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookmarkViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BookmarkListUiState.init())
    val uiState: StateFlow<BookmarkListUiState> = _uiState.asStateFlow()

    fun updateBookmark(
        list: List<BookmarkListItem>
    ) {
        _uiState.update { prev ->
            prev.copy(
                list = list
            )
        }
    }

}