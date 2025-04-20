package com.esm.taskify_news.domain.usecases.news

import com.esm.taskify_news.data.local.NewsDao
import com.esm.taskify_news.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article = article)
    }

}