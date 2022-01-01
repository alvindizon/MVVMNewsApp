package com.androiddevs.mvvmnewsapp.ui.savednews

import androidx.paging.PagingData
import com.androiddevs.mvvmnewsapp.data.NewsRepo
import com.androiddevs.mvvmnewsapp.data.util.toUi
import com.androiddevs.mvvmnewsapp.ui.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveNewsToDbUseCase @Inject constructor(private val newsRepo: NewsRepo){

    fun getSavedNews(): Flow<PagingData<Article>> {
        return newsRepo.getSavedNews().map { dbList ->
            val mapped = dbList.map{
                it.toUi()
            }
            PagingData.from(mapped)
        }
    }
}
