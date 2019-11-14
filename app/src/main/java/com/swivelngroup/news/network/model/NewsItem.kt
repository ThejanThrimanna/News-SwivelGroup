package com.swivelngroup.news.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by thejanthrimanna on 2019-11-14.
 */
class NewsItem {
    @SerializedName("source")
    var source: Source? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("urlToImage")
    var urlToImage: String? = null
    @SerializedName("publishedAt")
    var publishedAt: String? = null
    @SerializedName("content")
    var content: String? = null
}
