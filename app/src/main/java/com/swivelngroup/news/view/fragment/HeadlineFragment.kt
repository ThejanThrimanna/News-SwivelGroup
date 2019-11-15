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
import com.swivelngroup.news.network.model.NewsItem
import com.swivelngroup.news.utils.KEYWORD_NEWS
import com.swivelngroup.news.utils.RecyclerItemClickListenr
import com.swivelngroup.news.view.activity.MainActivity
import com.swivelngroup.news.view.activity.NewsDetailsActivity
import com.swivelngroup.news.viewmodel.HeadlineViewModel
import com.swivelngroup.news.viewmodel.Status
import com.swivelngroup.news.viewmodel.ViewModelState
import kotlinx.android.synthetic.main.fragment_headline.*

/**
 * A simple [Fragment] subclass.
 */
class HeadlineFragment : BaseFragment() {

    private var fragmentView: View? = null

    companion object {
        fun newInstance(): HeadlineFragment {
            return HeadlineFragment()
        }
    }

    lateinit var viewModel: HeadlineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (fragmentView != null) {
            return fragmentView
        }
        val view =
            inflater.inflate(com.swivelngroup.news.R.layout.fragment_headline, container, false)
        fragmentView = view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViewModel()
        initSubscription()
        setUpRecyclerView()
        initAction()
        viewModel.getHeadlines()
    }

    private fun initAction() {
        refresh.setOnRefreshListener {
            viewModel.getHeadlines()
            refresh.isRefreshing = false
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HeadlineViewModel::class.java)
    }

    private fun initSubscription() {
        viewModel.state!!.observe(this, Observer<ViewModelState> {
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
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = viewModel.headlineAdapter
        recyclerview.addOnItemTouchListener(
            RecyclerItemClickListenr(
                activity as MainActivity,
                recyclerview,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemLongClick(view: View?, position: Int) {
                    }

                    override fun onItemClick(view: View, position: Int) {
                        OpenNews(viewModel.headlineAdapter.getItem(position))
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
