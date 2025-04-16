package com.esm.taskify_news.domain.usecases.news

import androidx.paging.PagingData
import com.esm.taskify_news.domain.model.Article
import com.esm.taskify_news.domain.model.NewsRepository

import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}