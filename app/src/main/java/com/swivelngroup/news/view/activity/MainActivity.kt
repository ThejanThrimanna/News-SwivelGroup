package com.swivelngroup.news.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swivelngroup.news.R
import com.swivelngroup.news.view.adapter.HomeViewPageAdapter
import com.swivelngroup.news.view.fragment.CustomFragment
import com.swivelngroup.news.view.fragment.HeadlineFragment
import com.swivelngroup.news.view.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var homeFragment = HeadlineFragment.newInstance()
    private var customFragment = CustomFragment.newInstance()
    private var profileFragment = ProfileFragment.newInstance()
    private lateinit var homeViewPageAdapter: HomeViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initView()
    }
    private fun initView() {
        setStatePageAdapter()
    }

    private fun setStatePageAdapter() {
        homeViewPageAdapter = HomeViewPageAdapter(supportFragmentManager)
        homeViewPageAdapter.addFragments(homeFragment, getString(R.string.headline))
        homeViewPageAdapter.addFragments(customFragment, getString(R.string.custom))
        homeViewPageAdapter.addFragments(profileFragment, getString(R.string.profile))
        viewPager.adapter = homeViewPageAdapter
        viewPager.offscreenPageLimit = 3
        tabs.setupWithViewPager(viewPager, true)
    }
}
