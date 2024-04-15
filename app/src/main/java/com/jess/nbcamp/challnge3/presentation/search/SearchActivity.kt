package com.jess.nbcamp.challnge3.presentation.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jess.nbcamp.challnge3.databinding.SearchActivityBinding

class SearchActivity : AppCompatActivity() {

    private val binding: SearchActivityBinding by lazy {
        SearchActivityBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.onSearch("kotlin")
    }
}