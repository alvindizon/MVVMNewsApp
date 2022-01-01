package com.androiddevs.mvvmnewsapp.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.paging.PagingData
import com.androiddevs.mvvmnewsapp.ui.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

private const val CHAR_LIMIT = 3

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase,
    state: SavedStateHandle
) : ViewModel() {

    val searchQuery = state.getLiveData("searchQuery", "")

    @FlowPreview
    val onQuerySearched: Flow<PagingData<Article>> =
        searchQuery.asFlow()
            .debounce(300L)
            .filter { it.length >= CHAR_LIMIT }
            .flatMapLatest {
                searchNewsUseCase.searchNews(it)
            }
}
