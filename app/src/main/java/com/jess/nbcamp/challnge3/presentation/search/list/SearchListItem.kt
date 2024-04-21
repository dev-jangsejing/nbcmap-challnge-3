package com.jess.nbcamp.challnge3.presentation.search.list

import java.util.Date

sealed interface SearchListItem {

    data class ImageItem(
        val title: String?,
        val thumbnail: String?,
        val date: Date?
    ) : SearchListItem
}