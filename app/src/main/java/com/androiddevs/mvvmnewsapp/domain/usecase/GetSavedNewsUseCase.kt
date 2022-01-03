package com.androiddevs.mvvmnewsapp.domain.usecase

import com.androiddevs.mvvmnewsapp.data.util.toUi
import com.androiddevs.mvvmnewsapp.domain.NewsRepo
import com.androiddevs.mvvmnewsapp.ui.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSavedNewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    fun getSavedNews(): Flow<List<Article>> {
        return newsRepo.getSavedNews().map { dbList ->
            dbList.map {
                it.toUi()
            }
        }
    }
}
