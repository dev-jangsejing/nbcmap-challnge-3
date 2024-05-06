package com.jess.nbcamp.challnge3.domain.search.repository

import com.jess.nbcamp.challnge3.domain.search.model.ImageDocumentEntity
import com.jess.nbcamp.challnge3.domain.search.model.SearchEntity
import com.jess.nbcamp.challnge3.domain.search.model.VideoDocumentEntity

interface SearchRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchEntity<ImageDocumentEntity>


    suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchEntity<VideoDocumentEntity>
}