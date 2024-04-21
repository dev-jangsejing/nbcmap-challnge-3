package com.jess.nbcamp.challnge3.presentation.search

data class SearchUiState(
    val list: List<SearchItem>,
    val isLoading: Boolean
) {

    companion object {

        fun init() = SearchUiState(
            list = emptyList(),
            isLoading = false
        )
    }
}