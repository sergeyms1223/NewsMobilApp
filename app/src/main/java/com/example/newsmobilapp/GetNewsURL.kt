package com.example.newsmobilapp

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException

data class NewsResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: NewsData
)

data class NewsData(
    @SerializedName("count") val count: Int,
    @SerializedName("news") val news: List<NewsItem>
)

data class NewsItem(
    @SerializedName("id") val id_news: Int,
    @SerializedName("type") val type: String,
    @SerializedName("title") val title: String,
    @SerializedName("img") val imageUrl: String,
    @SerializedName("news_date") val newsDate: String,
    @SerializedName("news_date_uts") val newsDateUts: String,
    @SerializedName("annotation") val annotation: String,
    @SerializedName("mobile_url") val mobileUrl: String
)

interface NewsService {
    @GET("list")
    fun getNews(): retrofit2.Call<NewsResponse>
}

class GetNewsURL() {
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://ws-tszh-1c-test.vdgb-soft.ru/api/mobile/news/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun execute(): List<NewsItem>? {
        val service = retrofit.create(NewsService::class.java)
        val call = service.getNews()

        return try {
            val response = call.execute()

            if (response.isSuccessful) {
                val newsResponse = response.body()
                newsResponse?.data?.news
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}