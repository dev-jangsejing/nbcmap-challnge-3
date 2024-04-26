package com.jess.nbcamp.challnge3.presentation.search.list

import java.util.Date

sealed interface SearchListItem {

    val id: String
    val title:String?
    val bookmarked: Boolean

    data class ImageItem(
        override val id: String,
        override val title: String?,
        val thumbnail: String?,
        val date: Date?,
        override val bookmarked: Boolean = false,
    ) : SearchListItem
}