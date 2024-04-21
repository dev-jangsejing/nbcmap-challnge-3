package com.jess.nbcamp.challnge3.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.jess.nbcamp.challnge3.data.repository.SearchRepositoryImpl
import com.jess.nbcamp.challnge3.domain.search.model.SearchImageEntity
import com.jess.nbcamp.challnge3.domain.search.usecase.SearchGetImageUseCase
import com.jess.nbcamp.challnge3.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchImage: SearchGetImageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState.init())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun onSearch(
        query: String
    ) = viewModelScope.launch {
        showLoading(true)
        runCatching {
            val items = createItems(
                images = searchImage(query),
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
        images: SearchImageEntity,
    ): List<SearchItem> {

        fun createImageItems(
            images: SearchImageEntity,
        ): List<SearchItem.ImageItem> = images.documents?.map { document ->
            SearchItem.ImageItem(
                title = document.displaySitename,
                thumbnail = document.thumbnailUrl,
                date = document.datetime
            )
        }.orEmpty()

        return arrayListOf<SearchItem>().apply {
            addAll(createImageItems(images))
        }.sortedByDescending {
            it.date
        }
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val repository = SearchRepositoryImpl(RetrofitClient.search)

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T = SearchViewModel(
        SearchGetImageUseCase(repository)
    ) as T
}