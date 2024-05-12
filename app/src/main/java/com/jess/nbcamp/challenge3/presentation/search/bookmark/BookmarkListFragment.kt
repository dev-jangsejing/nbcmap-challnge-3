package com.jess.nbcamp.challenge3.presentation.search.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jess.nbcamp.challenge3.databinding.BookmarkListFragmentBinding
import com.jess.nbcamp.challenge3.presentation.search.shared.SearchSharedEvent
import com.jess.nbcamp.challenge3.presentation.search.shared.SearchSharedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BookmarkListFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarkListFragment()
    }

    private var _binding: BookmarkListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookmarkViewModel by viewModels()

    private val sharedViewModel: SearchSharedViewModel by lazy {
        ViewModelProvider(requireActivity())[SearchSharedViewModel::class.java]
    }

    private val adapter: BookmarkListAdapter by lazy {
        BookmarkListAdapter { item ->

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookmarkListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        list.adapter = adapter
    }

    private fun initViewModel() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { state ->
                    onBind(state)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.event.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { event ->
                    onSharedEvent(event)
                }
        }
    }

    private fun onBind(
        state: BookmarkListUiState
    ) = with(binding) {
        adapter.submitList(state.list)
    }

    private fun onSharedEvent(
        event: SearchSharedEvent
    ) {
        when (event) {
            is SearchSharedEvent.UpdateBookmark -> viewModel.updateBookmark(event.list)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}