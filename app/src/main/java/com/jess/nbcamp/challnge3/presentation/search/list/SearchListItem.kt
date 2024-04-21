package com.jess.nbcamp.challnge3.presentation.search.list

import java.util.Date

sealed class SearchListItem(
    open val date: Date?
) {

    data class ImageItem(
        val title: String?,
        val thumbnail: String?,
        override val date: Date?
    ) : SearchListItem(date)
}