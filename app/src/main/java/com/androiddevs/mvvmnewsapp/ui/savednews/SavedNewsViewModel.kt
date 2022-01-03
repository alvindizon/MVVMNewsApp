package com.androiddevs.mvvmnewsapp.ui.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.domain.usecase.DeleteArticleUseCase
import com.androiddevs.mvvmnewsapp.domain.usecase.GetSavedNewsUseCase
import com.androiddevs.mvvmnewsapp.domain.usecase.SaveArticleUseCase
import com.androiddevs.mvvmnewsapp.ui.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val saveArticleUseCase: SaveArticleUseCase
) :
    ViewModel() {

    fun onViewCreated(): Flow<List<Article>> = getSavedNewsUseCase.getSavedNews()

    fun onItemSwipe(articleUrl: String) = viewModelScope.launch {
        deleteArticleUseCase.deleteArticleByUrl(articleUrl)
    }

    fun onUndoClick(article: Article) = viewModelScope.launch {
        saveArticleUseCase.saveArticle(article)
    }
}
