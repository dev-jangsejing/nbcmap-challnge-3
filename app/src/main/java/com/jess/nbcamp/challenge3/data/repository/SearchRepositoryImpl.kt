package com.jess.nbcamp.challenge3.data.repository

import com.jess.nbcamp.challenge3.data.remote.SearchRemoteDatasource
import com.jess.nbcamp.challenge3.domain.search.model.SearchEntity
import com.jess.nbcamp.challenge3.domain.search.model.VideoDocumentEntity
import com.jess.nbcamp.challenge3.domain.search.model.toImageEntity
import com.jess.nbcamp.challenge3.domain.search.model.toVideoEntity
import com.jess.nbcamp.challenge3.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDatasource: SearchRemoteDatasource,
) : SearchRepository {

    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ) = remoteDatasource.getSearchImage(
        query,
        sort,
        page,
        size
    ).toImageEntity()

    override suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): SearchEntity<VideoDocumentEntity> = remoteDatasource.getSearchVideo(
        query,
    ).toVideoEntity()
}