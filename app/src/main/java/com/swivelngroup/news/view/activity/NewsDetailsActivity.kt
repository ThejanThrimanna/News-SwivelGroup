package com.swivelngroup.news.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.swivelngroup.news.R
import com.swivelngroup.news.network.model.NewsItem
import com.swivelngroup.news.utils.AppUtils
import com.swivelngroup.news.utils.KEYWORD_NEWS
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {

    lateinit var currentNews: NewsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        init()
    }

    private fun init() {
        initView()
        initData()
        initAction()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_white);
    }

    private fun initAction() {
        tvLink.setOnClickListener {
            openTheRealNews(tvLink.text.toString())
        }

    }

    private fun initData() {
        currentNews = intent.getParcelableExtra(KEYWORD_NEWS)!!
        tvTitle.text = currentNews.title
        tvDesc.text = currentNews.description
        if (currentNews.content != null)
            tvContent.text = Html.fromHtml(currentNews.content)
        tvLink.text = Html.fromHtml(currentNews.url)
        if (currentNews.urlToImage != null)
            AppUtils.loadImageGlide(this, currentNews.urlToImage!!, ivImage)
    }

    private fun openTheRealNews(link: String) {
        val packageManager = getPackageManager()
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        if (browserIntent.resolveActivity(packageManager) != null)
            startActivity(browserIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
