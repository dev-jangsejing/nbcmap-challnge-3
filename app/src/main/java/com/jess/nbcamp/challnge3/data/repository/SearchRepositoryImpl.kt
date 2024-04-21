package com.jess.nbcamp.challnge3.data.repository

import com.jess.nbcamp.challnge3.data.remote.SearchRemoteDatasource
import com.jess.nbcamp.challnge3.domain.search.model.toEntity
import com.jess.nbcamp.challnge3.domain.search.repository.SearchRepository

class SearchRepositoryImpl(
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
    ).toEntity()
}