package com.androiddevs.mvvmnewsapp.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.data.NewsRepo
import com.androiddevs.mvvmnewsapp.data.db.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val newsRepo: NewsRepo) : ViewModel() {

    fun onFabClick(article: Article) = viewModelScope.launch {
        newsRepo.saveArticle(article)
    }
}
