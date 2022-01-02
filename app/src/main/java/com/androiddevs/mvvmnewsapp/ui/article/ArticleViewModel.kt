package com.androiddevs.mvvmnewsapp.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.domain.usecase.SaveArticleUseCase
import com.androiddevs.mvvmnewsapp.ui.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val saveArticleUseCase: SaveArticleUseCase) :
    ViewModel() {

    fun onFabClick(article: Article) = viewModelScope.launch {
        saveArticleUseCase.saveArticle(article)
    }
}
