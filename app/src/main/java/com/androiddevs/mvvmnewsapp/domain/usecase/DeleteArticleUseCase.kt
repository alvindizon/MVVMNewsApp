package com.androiddevs.mvvmnewsapp.domain.usecase

import com.androiddevs.mvvmnewsapp.domain.NewsRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteArticleUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    suspend fun deleteArticleByUrl(articleUrl: String) {
        newsRepo.deleteArticleByUrl(articleUrl)
    }
}
