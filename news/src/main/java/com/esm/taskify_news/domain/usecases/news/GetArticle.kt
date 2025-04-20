package com.esm.taskify_news.domain.usecases.news

import com.esm.taskify_news.data.local.NewsDao
import com.esm.taskify_news.domain.model.Article

class GetArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url = url)
    }

}