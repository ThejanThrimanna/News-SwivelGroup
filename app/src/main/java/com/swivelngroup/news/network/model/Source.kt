package com.swivelngroup.news.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by thejanthrimanna on 2019-11-14.
 */
class Source{
    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
}
