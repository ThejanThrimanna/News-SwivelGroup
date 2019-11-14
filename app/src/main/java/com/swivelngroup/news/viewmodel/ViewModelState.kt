package com.swivelngroup.news.viewmodel

/**
 * Created by Thejan_Thrimanna on 1/8/19.
 */

class ViewModelState constructor(
    var status: Status,
    var error: Throwable? = null) {
    companion object {
        fun loading(): ViewModelState {
            return ViewModelState(Status.LOADING)
        }
        fun success(): ViewModelState {
            return ViewModelState(Status.SUCCESS)
        }
        fun error(): ViewModelState {
            return ViewModelState(Status.ERROR)
        }
        fun list_empty(): ViewModelState {
            return ViewModelState(Status.LIST_EMPTY)
        }
    }
}
