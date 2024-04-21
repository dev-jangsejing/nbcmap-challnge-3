package com.jess.nbcamp.challnge3.presentation.search.list

data class SearchListUiState(
    val list: List<SearchListItem>,
    val isLoading: Boolean
) {

    companion object {

        fun init() = SearchListUiState(
            list = emptyList(),
            isLoading = false
        )
    }
}