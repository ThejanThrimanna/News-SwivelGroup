package com.swivelngroup

import android.app.Application

/**
 * Created by thejanthrimanna on 2019-11-15.
 */
class NewsApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: NewsApplication
    }
}
