package com.jess.nbcamp.challenge3.presentation.search.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jess.nbcamp.challenge3.databinding.SearchMainActivityBinding

class SearchMainActivity : AppCompatActivity() {

    private val binding: SearchMainActivityBinding by lazy {
        SearchMainActivityBinding.inflate(layoutInflater)
    }

    private val viewPagerAdapter by lazy {
        SearchMainViewPagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = viewPagerAdapter.itemCount

        // TabLayout x ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()
    }

}