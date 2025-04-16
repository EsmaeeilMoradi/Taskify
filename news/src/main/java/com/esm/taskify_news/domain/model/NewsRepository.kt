package com.esm.taskify_news.domain.model

import com.esm.taskify_news.domain.model.Article
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
}