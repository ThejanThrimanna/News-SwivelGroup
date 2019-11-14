package com.swivelngroup.news.network

/**
 * Created by thejanthrimanna on 2019-11-14.
 */
class ServiceResponse<T>{
    var status:String? = null
    var totalResults:Int? = null
    var articles:T? = null
}
