package com.androiddevs.mvvmnewsapp.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.androiddevs.mvvmnewsapp.data.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

private const val CHAR_LIMIT = 3

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo,
    private val state: SavedStateHandle
) : ViewModel() {

    val searchQuery = state.getLiveData("searchQuery", "")

    @FlowPreview
    val onQuerySearched =
        searchQuery.asFlow()
            .debounce(300L)
            .filter { it.length >= CHAR_LIMIT }
            .flatMapLatest {
                newsRepo.searchNews(it)
            }
}
