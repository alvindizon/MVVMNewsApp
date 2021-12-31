package com.androiddevs.mvvmnewsapp.ui.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.androiddevs.mvvmnewsapp.data.NewsRepo
import com.androiddevs.mvvmnewsapp.data.api.model.Article
import com.androiddevs.mvvmnewsapp.data.util.toDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(private val newsRepo: NewsRepo) : ViewModel() {

    fun onViewCreated(): Flow<PagingData<Article>> {
        return newsRepo.getSavedNews().map { dbList ->
            val apiList = dbList.map {
                it.toDb()
            }
            PagingData.from(apiList)
        }.cachedIn(viewModelScope)
    }
}
