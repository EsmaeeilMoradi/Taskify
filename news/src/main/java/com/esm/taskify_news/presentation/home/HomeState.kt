package com.esm.taskify_news.presentation.home


data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)