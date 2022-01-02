package com.androiddevs.mvvmnewsapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.androiddevs.mvvmnewsapp.domain.NewsRepo
import com.androiddevs.mvvmnewsapp.data.util.toUi
import com.androiddevs.mvvmnewsapp.ui.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchNewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    fun searchNews(query: String) : Flow<PagingData<Article>> {
        return newsRepo.searchNews(query).map { apiArticles ->
            apiArticles.map {
                it.toUi()
            }
        }
    }
}
