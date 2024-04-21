package com.jess.nbcamp.challnge3.presentation.search.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jess.nbcamp.challnge3.databinding.BookmarkListFragmentBinding

class BookmarkListFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarkListFragment()
    }

    private var _binding: BookmarkListFragmentBinding? = null
    private val binding get() = _binding!!

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

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}