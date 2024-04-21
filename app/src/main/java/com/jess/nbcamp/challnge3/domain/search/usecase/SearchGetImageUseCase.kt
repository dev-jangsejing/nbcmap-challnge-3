package com.jess.nbcamp.challnge3.domain.search.usecase

import com.jess.nbcamp.challnge3.domain.search.model.SearchImageEntity
import com.jess.nbcamp.challnge3.domain.search.repository.SearchRepository

class SearchGetImageUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchImageEntity = repository.getSearchImage(
        query,
        sort,
        page,
        size
    )
}