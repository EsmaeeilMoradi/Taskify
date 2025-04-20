package com.esm.taskify_news.domain.usecases.news

import com.esm.taskify_news.data.local.NewsDao
import com.esm.taskify_news.domain.model.Article
import kotlinx.coroutines.flow.Flow


class GetArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}