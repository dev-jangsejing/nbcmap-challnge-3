package com.jess.nbcamp.challnge3.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.jess.nbcamp.challnge3.data.repository.SearchRepositoryImpl
import com.jess.nbcamp.challnge3.network.RetrofitClient
import com.jess.nbcamp.challnge3.presentation.search.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {

    fun onSearch(query: String) = viewModelScope.launch {
        runCatching {
            val response = searchRepository.getSearchImage(query)
            Log.d("jess", response.toString())
        }.onFailure {
            if (it.message == "인증만료") {
                // 갱신 - ui 처리
            }
        }
    }

}

class SearchViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {

        return SearchViewModel(
            SearchRepositoryImpl(RetrofitClient.search)
        ) as T
    }
}