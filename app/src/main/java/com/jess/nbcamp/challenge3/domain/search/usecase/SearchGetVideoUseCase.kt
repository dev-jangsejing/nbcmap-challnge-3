package com.jess.nbcamp.challenge3.domain.search.usecase

import com.jess.nbcamp.challenge3.domain.search.model.SearchEntity
import com.jess.nbcamp.challenge3.domain.search.model.VideoDocumentEntity
import com.jess.nbcamp.challenge3.domain.search.repository.SearchRepository

class SearchGetVideoUseCase(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchEntity<VideoDocumentEntity> = repository.getSearchVideo(
        query,
        sort,
        page,
        size
    )
}