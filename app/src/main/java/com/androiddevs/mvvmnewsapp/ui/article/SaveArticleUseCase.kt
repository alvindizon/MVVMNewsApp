package com.androiddevs.mvvmnewsapp.ui.article

import com.androiddevs.mvvmnewsapp.data.NewsRepo
import com.androiddevs.mvvmnewsapp.data.util.toDb
import com.androiddevs.mvvmnewsapp.ui.Article
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveArticleUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    suspend fun saveArticle(article: Article): Long {
        return newsRepo.saveArticle(article.toDb())
    }
}
