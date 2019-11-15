package com.swivelngroup.news.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.swivelngroup.news.R

class SplashActivity : AppCompatActivity() {
    var view1 : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
         view1 = findViewById<View>(R.id.text) as TextView
    }

    override fun onResume() {
        super.onResume()
        val animation0 = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        view1!!.startAnimation(animation0)
        Handler().postDelayed(runb(), 500)
    }

    internal inner class runb : Runnable {

        override fun run() {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
