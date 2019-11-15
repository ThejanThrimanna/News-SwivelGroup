package com.swivelngroup.news.utils

import android.content.Context
import com.swivelngroup.NewsApplication

/**
 * Created by thejanthrimanna on 2019-11-15.
 */
object SharedPref {
    val FILE_KEY = "news.file.key"
    val SELECTED_CATEGORY_INDEX = "selected.category.index"
    val SELECTED_LANGUAGE_INDEX = "selected.language.index"
    val SELECTED_COUNTRY_INDEX = "selected.country.index"
    val SELECTED_CATEGORY_VALUE = "selected.category.value"
    val SELECTED_LANGUAGE_VALUE = "selected.language.value"
    val SELECTED_COUNTRY_VALUE = "selected.country.value"

    fun saveString(key: String, value: String) {
        val editor = NewsApplication.instance!!.getSharedPreferences(
            SharedPref.FILE_KEY, Context.MODE_PRIVATE).edit()
        editor.putString(key, value)
        editor.commit()
    }
    fun getString(key: String, defaultval: String): String? {
        val prefs = NewsApplication.instance!!.getSharedPreferences(SharedPref.FILE_KEY,
            Context.MODE_PRIVATE)
        return prefs.getString(key, defaultval)
    }
    fun saveInteger(key: String, value: Int?) {
        val editor = NewsApplication.instance!!.getSharedPreferences(
            SharedPref.FILE_KEY, Context.MODE_PRIVATE).edit()
        editor.putInt(key, value!!)
        editor.commit()
    }
    fun getInteger(key: String, defaultval: Int): Int {
        val prefs = NewsApplication.instance!!.getSharedPreferences(SharedPref.FILE_KEY,
            Context.MODE_PRIVATE)
        return prefs.getInt(key, defaultval)
    }
}
