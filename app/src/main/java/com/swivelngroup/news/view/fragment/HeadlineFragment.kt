package com.swivelngroup.news.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.swivelngroup.news.R
import com.swivelngroup.news.network.model.NewsItem
import com.swivelngroup.news.utils.KEYWORD_NEWS
import com.swivelngroup.news.utils.RecyclerItemClickListenr
import com.swivelngroup.news.view.activity.MainActivity
import com.swivelngroup.news.view.activity.NewsDetailsActivity
import com.swivelngroup.news.viewmodel.HeadlineViewModel
import com.swivelngroup.news.viewmodel.Status
import com.swivelngroup.news.viewmodel.ViewModelState

/**
 * A simple [Fragment] subclass.
 */
class HeadlineFragment : BaseFragment() {

    private var mFragmentView: View? = null
    private var mRecyclerview: RecyclerView? = null
    private var mRefresh: SwipeRefreshLayout? = null
    lateinit var mViewModel: HeadlineViewModel

    companion object {
        fun newInstance(): HeadlineFragment {
            return HeadlineFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mFragmentView != null) {
            return mFragmentView
        }
        val view =
            inflater.inflate(com.swivelngroup.news.R.layout.fragment_headline, container, false)
        mFragmentView = view
        mRecyclerview = view.findViewById(R.id.recyclerview)
        mRefresh = view.findViewById(R.id.refresh)
        init()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser && ::mViewModel.isInitialized){
            mViewModel.getHeadlines()
        }
    }

    private fun init() {
        initViewModel()
        initSubscription()
        setUpRecyclerView()
        initAction()
        mViewModel.getHeadlines()
    }

    private fun initAction() {
        mRefresh!!.setOnRefreshListener {
            mViewModel.getHeadlines()
            mRefresh!!.isRefreshing = false
        }
    }

    private fun initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(HeadlineViewModel::class.java)
    }

    private fun initSubscription() {
        mViewModel.state!!.observe(this, Observer<ViewModelState> {
            it?.let {
                update(it)
            }
        })
    }

    fun update(state: ViewModelState) {
        when (state.status) {
            Status.LOADING -> {
                showProgress(activity as MainActivity)
            }
            Status.SUCCESS -> {
                hideProgress()
            }
            Status.ERROR -> {
                hideProgress()
            }
            Status.TIMEOUT -> {
                hideProgress()
            }
            Status.LIST_EMPTY -> {
                hideProgress()
            }
        }
    }

    private fun setUpRecyclerView() {
        mRecyclerview!!.setHasFixedSize(true)
        mRecyclerview!!.layoutManager = LinearLayoutManager(activity)
        mRecyclerview!!.adapter = mViewModel.headlineAdapter
        mRecyclerview!!.addOnItemTouchListener(
            RecyclerItemClickListenr(
                activity as MainActivity,
                mRecyclerview!!,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemLongClick(view: View?, position: Int) {
                    }

                    override fun onItemClick(view: View, position: Int) {
                        OpenNews(mViewModel.headlineAdapter.getItem(position))
                    }
                })
        )
    }

    private fun OpenNews(item: NewsItem) {
        var intetnt = Intent(activity as MainActivity, NewsDetailsActivity::class.java)
        intetnt.putExtra(KEYWORD_NEWS, item)
        startActivity(intetnt)
    }

}
