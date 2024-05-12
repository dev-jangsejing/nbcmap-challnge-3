package com.jess.nbcamp.challenge3.presentation.search.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.jess.nbcamp.challenge3.data.repository.SearchRepositoryImpl
import com.jess.nbcamp.challenge3.domain.search.model.ImageDocumentEntity
import com.jess.nbcamp.challenge3.domain.search.model.SearchEntity
import com.jess.nbcamp.challenge3.domain.search.model.VideoDocumentEntity
import com.jess.nbcamp.challenge3.domain.search.repository.SearchRepository
import com.jess.nbcamp.challenge3.domain.search.usecase.SearchGetImageUseCase
import com.jess.nbcamp.challenge3.domain.search.usecase.SearchGetVideoUseCase
import com.jess.nbcamp.challenge3.network.RetrofitClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchImage: SearchGetImageUseCase,
    private val searchVideo: SearchGetVideoUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchListUiState.init())
    val uiState: StateFlow<SearchListUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SearchListEvent>()
    val event: SharedFlow<SearchListEvent> = _event.asSharedFlow()

    fun onSearch(
        query: String
    ) = viewModelScope.launch {
        showLoading(true)
        runCatching {
            val items = createItems(
                images = searchImage(query),
                videos = searchVideo(query)
            )

            _uiState.update { prevState ->
                prevState.copy(
                    list = items,
                    isLoading = false
                )
            }
        }.onFailure {
            // network, error, ...
            Log.e("jess", it.message.toString())
            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        _uiState.update { prevState ->
            prevState.copy(
                isLoading = isLoading
            )
        }
    }

    private fun createItems(
        images: SearchEntity<ImageDocumentEntity>,
        videos: SearchEntity<VideoDocumentEntity>,
    ): List<SearchListItem> {

        fun createImageItems(
            images: SearchEntity<ImageDocumentEntity>
        ): List<SearchListItem.ImageItem> = images.documents?.map { document ->
            SearchListItem.ImageItem(
                id = document.id,
                title = document.docUrl,
                thumbnail = document.thumbnailUrl,
                date = document.datetime
            )
        }.orEmpty()

        fun createVideoItems(
            videos: SearchEntity<VideoDocumentEntity>
        ): List<SearchListItem.VideoItem> = videos.documents?.map { document ->
            SearchListItem.VideoItem(
                id = document.id,
                title = document.title,
                thumbnail = document.thumbnail,
                date = document.datetime
            )
        }.orEmpty()

        return arrayListOf<SearchListItem>().apply {
            addAll(createImageItems(images))
            addAll(createVideoItems(videos))
        }.sortedByDescending {// 내림차순
            it.date
        }
    }

    fun onBookmark(
        item: SearchListItem,
    ) = viewModelScope.launch {
        val mutableList = uiState.value.list.toMutableList()

        val position = mutableList.indexOfFirst {
            it.id == item.id // 유니크값 대신 title 사용
        }

        // 수정
        mutableList[position] = when (item) {
            is SearchListItem.ImageItem -> item.copy(
                bookmarked = item.bookmarked.not()
            )

            is SearchListItem.VideoItem -> item.copy(
                bookmarked = item.bookmarked.not()
            )
        }

        _uiState.update { prev ->
            prev.copy(
                list = mutableList
            )
        }

        _event.emit(SearchListEvent.UpdateBookmark(uiState.value.list))

    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val repository: SearchRepository = SearchRepositoryImpl(RetrofitClient.search)


    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T = SearchViewModel(
        SearchGetImageUseCase(repository),
        SearchGetVideoUseCase(repository),
    ) as T
}