package com.esm.taskify_news.domain.usecases.news

import androidx.paging.PagingData
import com.esm.taskify_news.domain.model.Article
import com.esm.taskify_news.domain.model.NewsRepository

import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}