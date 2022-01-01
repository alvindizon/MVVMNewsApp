package com.androiddevs.mvvmnewsapp.ui.breakingnews

import androidx.paging.PagingData
import androidx.paging.map
import com.androiddevs.mvvmnewsapp.data.NewsRepo
import com.androiddevs.mvvmnewsapp.data.util.toUi
import com.androiddevs.mvvmnewsapp.ui.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBreakingNewsUseCase @Inject constructor(private val newsRepo: NewsRepo){

    fun getBreakingNews() : Flow<PagingData<Article>> {
        return newsRepo.getBreakingNews().map { apiArticles ->
            apiArticles.map {
                it.toUi()
            }
        }
    }
}
