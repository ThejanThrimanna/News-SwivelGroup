package com.swivelngroup.news.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide



/**
 * Created by thejanthrimanna on 2019-11-14.
 */
object AppUtils {
    fun loadImageGlide(activity: Context, i: String, iv: ImageView) {
        val circularProgressDrawable = CircularProgressDrawable(iv.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(activity)
            .load(i)
            .placeholder(circularProgressDrawable)
            .into(iv)
    }

}
