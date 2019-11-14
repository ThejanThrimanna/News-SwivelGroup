package com.swivelngroup.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swivelngroup.news.network.ApiInterface
import com.swivelngroup.news.network.ServiceGenerator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

/**
 * Created by thejanthrimanna on 2019-11-14.
 */
open class BaseViewModel:ViewModel(){
    val retrofit = ServiceGenerator.getClient()
    val apiCall = retrofit.create(ApiInterface::class.java)!!
    var state: MutableLiveData<ViewModelState>? = null
    private lateinit var disposables: CompositeDisposable

    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        initRx()
    }

    private fun initRx() {
        disposables = CompositeDisposable()
    }

    @Synchronized
    fun addDisposable(disposable: Disposable?) {
        if (disposable == null) return
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun setMessage(x: HttpException) {
        if (x.response().headers() != null && x.response().headers().get("message") != null) {
            message.postValue(x.response().headers().get("message"))

        } else {
            message.postValue("${x.code()} Error")
        }
    }
}
