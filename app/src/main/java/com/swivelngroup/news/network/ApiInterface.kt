package com.swivelngroup.news.network

import com.swivelngroup.news.network.model.NewsItem
import com.swivelngroup.news.utils.*
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
        @Query(KEYWORD_CATEGORY) category: String,
        @Query(KEYWORD_COUNTRY) country: String,
        @Query(KEYWORD_LANGUAGE) language: String
    ): Observable<ServiceResponse<List<NewsItem>>>

    @GET("everything")
    fun getEverything(
        @Query(KEYWORD_API_KEY) apiKey: String,
        @Query(KEYWORD_Q) q: String
    ): Observable<ServiceResponse<List<NewsItem>>>
}
