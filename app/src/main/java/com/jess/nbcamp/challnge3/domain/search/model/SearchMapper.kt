package com.jess.nbcamp.challnge3.domain.search.model

import com.jess.nbcamp.challnge3.data.model.ImageDocumentResponse
import com.jess.nbcamp.challnge3.data.model.MetaResponse
import com.jess.nbcamp.challnge3.data.model.SearchResponse
import com.jess.nbcamp.challnge3.data.model.VideoDocumentResponse
import java.util.UUID

fun MetaResponse.toEntity() = MetaEntity(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd
)

fun SearchResponse<ImageDocumentResponse>.toImageEntity() = SearchEntity(
    meta = meta?.toEntity(),
    documents = documents?.map { response ->
        response.toEntity()
    }
)

fun SearchResponse<VideoDocumentResponse>.toVideoEntity() = SearchEntity(
    meta = meta?.toEntity(),
    documents = documents?.map { response ->
        response.toEntity()
    }
)

fun ImageDocumentResponse.toEntity() = ImageDocumentEntity(
    id = UUID.randomUUID().toString(),
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    displaySitename = displaySitename,
    docUrl = docUrl,
    datetime = datetime,
)

fun VideoDocumentResponse.toEntity() = VideoDocumentEntity(
    id = UUID.randomUUID().toString(),
    title = title,
    url = url,
    playTime = playTime,
    thumbnail = thumbnail,
    author = author,
    datetime = datetime
)


