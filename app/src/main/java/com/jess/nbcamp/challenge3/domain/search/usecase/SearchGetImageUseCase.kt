package com.jess.nbcamp.challenge3.domain.search.usecase

import com.jess.nbcamp.challenge3.domain.search.model.ImageDocumentEntity
import com.jess.nbcamp.challenge3.domain.search.model.SearchEntity
import com.jess.nbcamp.challenge3.domain.search.repository.SearchRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchGetImageUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ): SearchEntity<ImageDocumentEntity> = repository.getSearchImage(
        query,
        sort,
        page,
        size
    )
}