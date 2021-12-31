package com.androiddevs.mvvmnewsapp.ui.breakingnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.androiddevs.mvvmnewsapp.data.NewsRepo
import com.androiddevs.mvvmnewsapp.data.api.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(private val newsRepo: NewsRepo) : ViewModel() {

    fun onViewCreated(): Flow<PagingData<Article>> {
        return newsRepo.getBreakingNews().cachedIn(viewModelScope)
    }
}