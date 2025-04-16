package com.esm.taskify_news.data.remote.dto

import com.esm.taskify_news.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)