package com.androiddevs.mvvmnewsapp.ui.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.androiddevs.mvvmnewsapp.ui.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(private val saveNewsToDbUseCase: SaveNewsToDbUseCase) :
    ViewModel() {

    fun onViewCreated(): Flow<PagingData<Article>> =
        saveNewsToDbUseCase.getSavedNews().cachedIn(viewModelScope)
}
