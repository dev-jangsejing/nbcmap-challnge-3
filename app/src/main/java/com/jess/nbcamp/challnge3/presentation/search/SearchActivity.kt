package com.jess.nbcamp.challnge3.presentation.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jess.nbcamp.challnge3.databinding.SearchActivityBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    private val binding: SearchActivityBinding by lazy {
        SearchActivityBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory()
    }

    private val adapter: SearchListAdapter by lazy {
        SearchListAdapter { item ->

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initViewModel()

        // for test
        viewModel.onSearch("kotlin")
    }

    private fun initView() = with(binding) {
        list.adapter = adapter
    }

    private fun initViewModel() = with(viewModel) {
        // collect : 새로운 데이터가 발행 되면 끝날 때 까지 기다림
        // collectLatest : 새로운 데이터가 발행되면 이전 처리르 취소하고 새로운 데이터 처리
        lifecycleScope.launch {
            uiState.flowWithLifecycle(lifecycle)
                .collectLatest { state ->
                    onBind(state)
                }
        }
    }

    private fun onBind(
        state: SearchUiState
    ) = with(binding) {
        adapter.submitList(state.list)
        progress.isVisible = state.isLoading
    }

}