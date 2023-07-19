@file:Suppress("DEPRECATION")

package com.example.newsmobilapp
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UpdateNewsSQL()
        val sql = NewsSQLite(this)
        sql.getAllNews()
    }
    //val text1 = findViewById<TextView>(R.id.text1)
    // "https://ws-tszh-1c-test.vdgb-soft.ru/api/mobile/news/list"

    private var thread = Thread {
        val getNewsTask = GetNewsURL()
        val bdNews = NewsSQLite(this)
        val newsList = getNewsTask.execute()
        if (newsList != null) {
            bdNews.clearBD()
            for (newsItem in newsList) {
                bdNews.insertNews(
                    id_news = newsItem.id_news,
                    type = newsItem.type,
                    title = newsItem.title,
                    image = newsItem.imageUrl,
                    news_date = newsItem.newsDate,
                    date_news_uts = newsItem.newsDateUts,
                    annotation = newsItem.annotation,
                    mobile_url = newsItem.mobileUrl
                )
            }
        }
        bdNews.close()
    }

    fun UpdateNewsSQL(){
        thread.start()
        thread.join()
    }

    fun UpdateNewsSpisok(){
        val bdNews = NewsSQLite(this)
        val spisok: ListView = findViewById(R.id.spisok)
        val dataList = mutableListOf<String>()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList)
        spisok.adapter = adapter

        dataList.add("Новость 1")
        dataList.add("Новость 2")
        adapter.notifyDataSetChanged()
    }

}


