package com.esm.taskify_news.data.remote

import com.esm.taskify_news.data.remote.dto.NewsResponse
import com.esm.taskify_news.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}