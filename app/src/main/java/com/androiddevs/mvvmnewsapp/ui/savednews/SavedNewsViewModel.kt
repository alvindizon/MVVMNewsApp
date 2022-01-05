package com.androiddevs.mvvmnewsapp.ui.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.domain.usecase.DeleteArticleUseCase
import com.androiddevs.mvvmnewsapp.domain.usecase.GetSavedNewsUseCase
import com.androiddevs.mvvmnewsapp.domain.usecase.SaveArticleUseCase
import com.androiddevs.mvvmnewsapp.ui.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val saveArticleUseCase: SaveArticleUseCase
) :
    ViewModel() {

    private val savedNewsEventChannel = Channel<SavedNewsEvent>(UNLIMITED)
    val savedNewsEvent = savedNewsEventChannel.receiveAsFlow()

    fun onViewCreated(): Flow<List<Article>> = getSavedNewsUseCase.getSavedNews()

    fun onItemSwipe(article: Article) = viewModelScope.launch {
        deleteArticleUseCase.deleteArticleByUrl(article.url)
        savedNewsEventChannel.send(SavedNewsEvent.ShowDeleteSuccessSnackbar(article))
    }

    fun onUndoClick(article: Article) = viewModelScope.launch {
        saveArticleUseCase.saveArticle(article)
    }

    sealed class SavedNewsEvent {
        data class ShowDeleteSuccessSnackbar(val article: Article) : SavedNewsEvent()
    }
}
