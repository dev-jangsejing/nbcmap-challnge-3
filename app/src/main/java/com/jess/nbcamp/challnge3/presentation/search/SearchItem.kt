package com.jess.nbcamp.challnge3.presentation.search

import java.util.Date

sealed class SearchItem(
    open val date: Date?
) {

    data class ImageItem(
        val title: String?,
        val thumbnail: String?,
        override val date: Date?
    ) : SearchItem(date)
}