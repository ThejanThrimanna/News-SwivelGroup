package com.swivelngroup.news.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.swivelngroup.news.R
import com.swivelngroup.news.network.model.NewsItem
import com.swivelngroup.news.utils.KEYWORD_NEWS
import com.swivelngroup.news.utils.RecyclerItemClickListenr
import com.swivelngroup.news.view.activity.MainActivity
import com.swivelngroup.news.view.activity.NewsDetailsActivity
import com.swivelngroup.news.viewmodel.HeadlineViewModel
import kotlinx.android.synthetic.main.fragment_headline.*

/**
 * A simple [Fragment] subclass.
 */
class HeadlineFragment : Fragment() {

    companion object {
        fun newInstance(): HeadlineFragment {
            return HeadlineFragment()
        }
    }

    lateinit var viewModel:HeadlineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViewModel()
        setUpRecyclerView()
        viewModel.getHeadlines()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HeadlineViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = viewModel.headlineAdapter
        recyclerview.addOnItemTouchListener(
            RecyclerItemClickListenr(activity as MainActivity, recyclerview,object:RecyclerItemClickListenr.OnItemClickListener{
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
