package com.jess.nbcamp.challnge3.presentation.search.repository

import com.jess.nbcamp.challnge3.presentation.search.model.SearchImageEntity

interface SearchRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchImageEntity
}