package com.swivelngroup.news.network

import com.swivelngroup.news.network.model.NewsItem
import com.swivelngroup.news.utils.KEYWORD_API_KEY
import com.swivelngroup.news.utils.KEYWORD_SOURCE
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by thejanthrimanna on 2019-11-14.
 */
interface ApiInterface {

    @GET("top-headlines")
    fun getHealines(
        @Query(KEYWORD_API_KEY) apiKey: String,
        @Query(KEYWORD_SOURCE) sources: String
    ): Observable<ServiceResponse<List<NewsItem>>>
}
