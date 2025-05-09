package com.esm.taskify_news.presentation.bookmark

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esm.taskify_news.domain.usecases.news.NewsUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        newsUseCases.getArticles().onEach {
            _state.value = _state.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }
}