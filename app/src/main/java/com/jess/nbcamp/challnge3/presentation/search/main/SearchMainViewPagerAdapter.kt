package com.jess.nbcamp.challnge3.presentation.search.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jess.nbcamp.challnge3.R
import com.jess.nbcamp.challnge3.presentation.search.bookmark.BookmarkListFragment
import com.jess.nbcamp.challnge3.presentation.search.list.SearchListFragment

class SearchMainViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        SearchMainTabModel(SearchListFragment.newInstance(), R.string.search_tab_search_title),
        SearchMainTabModel(BookmarkListFragment.newInstance(), R.string.search_tab_bookmark_title),
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position].fragment

    fun getTitle(position: Int): Int = fragments[position].title

}