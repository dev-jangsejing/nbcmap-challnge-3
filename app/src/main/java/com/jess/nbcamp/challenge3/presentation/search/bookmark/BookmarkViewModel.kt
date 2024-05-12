package com.jess.nbcamp.challenge3.presentation.search.bookmark

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(

) : ViewModel() {

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