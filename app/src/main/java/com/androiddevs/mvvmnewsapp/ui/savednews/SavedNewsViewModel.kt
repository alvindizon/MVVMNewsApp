package com.androiddevs.mvvmnewsapp.ui.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.androiddevs.mvvmnewsapp.domain.usecase.DeleteArticleUseCase
import com.androiddevs.mvvmnewsapp.domain.usecase.GetSavedNewsUseCase
import com.androiddevs.mvvmnewsapp.ui.Article
import com.androiddevs.mvvmnewsapp.domain.usecase.SaveArticleUseCase
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

    fun onViewCreated(): Flow<PagingData<Article>> =
        getSavedNewsUseCase.getSavedNews().cachedIn(viewModelScope)


    fun onItemSwipe(article: Article) = viewModelScope.launch {
        deleteArticleUseCase.deleteArticle(article)
    }

    fun onUndoClick(article: Article) = viewModelScope.launch {
        saveArticleUseCase.saveArticle(article)
    }
}
