package com.androiddevs.mvvmnewsapp.ui.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.androiddevs.mvvmnewsapp.data.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

private const val TAG = "SearchNewsViewModel"

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo,
    private val state: SavedStateHandle
) : ViewModel() {

    val searchQuery = state.getLiveData("searchQuery", "")

    val onQuerySearched = searchQuery.asFlow().flatMapLatest {
        Log.d(TAG, "query: $it")
        newsRepo.searchNews(it)
    }
}
