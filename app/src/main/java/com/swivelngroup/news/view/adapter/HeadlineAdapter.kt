package com.swivelngroup.news.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swivelngroup.news.R
import com.swivelngroup.news.network.model.NewsItem
import com.swivelngroup.news.utils.AppUtils
import kotlinx.android.synthetic.main.item_headline.view.*

/**
 * Created by thejanthrimanna on 2019-11-14.
 */

class HeadlineAdapter(internal var headlines: List<NewsItem?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HeadlinesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_headline, parent, false)
        )
    }

    private fun getItem(position: Int): NewsItem {
        return headlines[position]!!
    }

    override fun getItemCount(): Int {
        return headlines.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var headlineHolder = holder as HeadlinesViewHolder
        AppUtils.loadImageGlide(holder.itemView.context,getItem(position).urlToImage!!, headlineHolder.image)
        headlineHolder.title.text = getItem(position).title!!
    }

    class HeadlinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.tvTitle
        var image = itemView.ivImage
    }
}
