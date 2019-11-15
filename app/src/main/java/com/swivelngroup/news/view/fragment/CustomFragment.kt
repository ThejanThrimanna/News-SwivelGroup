package com.swivelngroup.news.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
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
import com.swivelngroup.news.viewmodel.CustomViewModel
import com.swivelngroup.news.viewmodel.Status
import com.swivelngroup.news.viewmodel.ViewModelState

/**
 * A simple [Fragment] subclass.
 */
class CustomFragment : BaseFragment() {

    private var mFragmentView: View? = null
    private var mRecyclerview: RecyclerView? = null
    private var mRefresh: SwipeRefreshLayout? = null
    private var mFilter: Spinner? = null
    private lateinit var mViewModel: CustomViewModel
    private lateinit var mProgressBar: ProgressBar
    private lateinit var messageFromResponse: String
    private var message = Observer<String> { msg ->
        messageFromResponse = msg!!
    }
    companion object {
        fun newInstance(): CustomFragment {
            return CustomFragment()
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
            inflater.inflate(R.layout.fragment_custom, container, false)
        mFragmentView = view
        mRecyclerview = view.findViewById(R.id.recyclerview)
        mRefresh = view.findViewById(R.id.refresh)
        mFilter = view.findViewById(R.id.tvFilter)
        mProgressBar = view.findViewById(R.id.progressBar)
        init()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && ::mViewModel.isInitialized) {
            mViewModel.getNews(mFilter!!.selectedItem.toString())
        }
    }

    private fun init() {
        initView()
        initViewModel()
        initSubscription()
        setUpRecyclerView()
        initAction()
        mViewModel.getNews(mFilter!!.selectedItem.toString())
    }

    private fun initView() {
        val adapter = ArrayAdapter<String>(
            activity as MainActivity,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.keywords)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mFilter!!.adapter = adapter
        mFilter!!.setSelection(1)
    }

    private fun initAction() {
        mRefresh!!.setOnRefreshListener {
            mViewModel.getNews(mFilter!!.selectedItem.toString())
            mRefresh!!.isRefreshing = false
        }

        mFilter!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                mViewModel.getNews(mFilter!!.selectedItem.toString())
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        })
    }

    private fun initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(CustomViewModel::class.java)
    }

    private fun initSubscription() {
        mViewModel.message.observe(this, message)
        mViewModel.state!!.observe(this, Observer<ViewModelState> {
            it?.let {
                update(it)
            }
        })
    }

    fun update(state: ViewModelState) {
        when (state.status) {
            Status.LOADING -> {
                mProgressBar.visibility = View.VISIBLE
            }
            Status.SUCCESS -> {
                mProgressBar.visibility = View.GONE
            }
            Status.ERROR -> {
                errorMessage()
            }
            Status.TIMEOUT -> {
                errorMessage()
            }
            Status.LIST_EMPTY -> {
                errorMessage()
            }
        }
    }

    private fun errorMessage(){
        mProgressBar.visibility = View.GONE
        Toast.makeText(activity as MainActivity,messageFromResponse,Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        mRecyclerview!!.setHasFixedSize(true)
        mRecyclerview!!.layoutManager = LinearLayoutManager(activity)
        mRecyclerview!!.adapter = mViewModel.newsAdapter
        mRecyclerview!!.addOnItemTouchListener(
            RecyclerItemClickListenr(
                activity as MainActivity,
                mRecyclerview!!,
                object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemLongClick(view: View?, position: Int) {
                    }

                    override fun onItemClick(view: View, position: Int) {
                        OpenNews(mViewModel.newsAdapter.getItem(position))
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
