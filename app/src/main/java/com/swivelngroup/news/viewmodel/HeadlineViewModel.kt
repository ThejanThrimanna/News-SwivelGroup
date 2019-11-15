package com.swivelngroup.news.viewmodel

import androidx.lifecycle.MutableLiveData
import com.swivelngroup.news.network.model.NewsItem
import com.swivelngroup.news.utils.API_KEY
import com.swivelngroup.news.utils.MESSAGE_ERROR
import com.swivelngroup.news.utils.MESSAGE_SOCKET_TIMEOUT
import com.swivelngroup.news.utils.SOURCE
import com.swivelngroup.news.view.adapter.NewsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException

/**
 * Created by thejanthrimanna on 2019-11-14.
 */
class HeadlineViewModel : BaseViewModel() {

    var newsAdapter = NewsAdapter(ArrayList())
    init {
        state = MutableLiveData()
    }

    fun getHeadlines() {
        addDisposable(
                apiCall.getHealines(API_KEY, SOURCE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { state!!.postValue(ViewModelState.loading()) }
                        .subscribe(
                                { headlines ->
                                    displayData(headlines.articles)
                                },

                                {
                                    if (it is SocketException || it is SocketTimeoutException) {
                                        message.postValue(MESSAGE_SOCKET_TIMEOUT)
                                        state!!.postValue(ViewModelState.error())
                                    } else {
                                        if (it is HttpException) {
                                            var httpException: HttpException = it
                                            setMessage(httpException)
                                        } else {
                                            message.postValue(MESSAGE_ERROR)
                                            state!!.postValue(ViewModelState.error())
                                        }
                                    }
                                    var list = ArrayList<NewsItem>()
                                    displayData(list)
                                })
        )
    }

    private fun displayData(headlines: List<NewsItem>?) {
        if (headlines.isNullOrEmpty()) {
            if (headlines != null) {
                state!!.postValue(ViewModelState.list_empty())
                newsAdapter.headlines = headlines
                newsAdapter.notifyDataSetChanged()
            }
        } else {
            state!!.postValue(ViewModelState.success())
            newsAdapter.headlines = headlines
            newsAdapter.notifyDataSetChanged()
        }
    }

}
