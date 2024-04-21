package com.jess.nbcamp.challnge3.domain.search.repository

import com.jess.nbcamp.challnge3.domain.search.model.SearchImageEntity

interface SearchRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): SearchImageEntity
}