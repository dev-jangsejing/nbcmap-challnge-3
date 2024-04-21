package com.jess.nbcamp.challnge3.domain.search.model

import com.jess.nbcamp.challnge3.data.model.ImageDocumentResponse
import com.jess.nbcamp.challnge3.data.model.MetaResponse
import com.jess.nbcamp.challnge3.data.model.SearchImageResponse

fun SearchImageResponse.toEntity() = SearchImageEntity(
    meta = meta?.toEntity(),
    documents = documents?.map {
        it.toEntity()
    }
)

fun MetaResponse.toEntity() = MetaEntity(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd,
)

fun ImageDocumentResponse.toEntity() = ImageDocumentEntity(
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    displaySitename = displaySitename,
    docUrl = docUrl,
    datetime = datetime,
)


