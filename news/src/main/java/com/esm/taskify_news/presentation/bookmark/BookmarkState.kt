package com.esm.taskify_news.presentation.bookmark

import com.esm.taskify_news.domain.model.Article


data class BookmarkState(
    val articles: List<Article> = emptyList()
)