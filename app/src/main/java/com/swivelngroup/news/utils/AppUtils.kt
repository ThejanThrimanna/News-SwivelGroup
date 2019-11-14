package com.swivelngroup.news.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by thejanthrimanna on 2019-11-14.
 */
object AppUtils {
    fun loadImageGlide(activity: Context, image: String, iv: ImageView) {
        Glide.with(activity)
            .load(image)
            .into(iv)
    }

}
