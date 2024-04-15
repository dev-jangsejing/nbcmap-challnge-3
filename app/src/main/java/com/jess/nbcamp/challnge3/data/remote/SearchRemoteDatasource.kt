package com.jess.nbcamp.challnge3.data.remote

import com.jess.nbcamp.challnge3.data.model.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDatasource {

    @GET("/v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): SearchImageResponse

}