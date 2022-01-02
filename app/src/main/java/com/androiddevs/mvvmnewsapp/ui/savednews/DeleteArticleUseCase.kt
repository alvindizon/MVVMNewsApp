package com.androiddevs.mvvmnewsapp.ui.savednews

import com.androiddevs.mvvmnewsapp.data.NewsRepo
import com.androiddevs.mvvmnewsapp.data.util.toDb
import com.androiddevs.mvvmnewsapp.ui.Article
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteArticleUseCase @Inject constructor(private val newsRepo: NewsRepo) {

    suspend fun deleteArticle(article: Article) {
        newsRepo.deleteArticle(article.toDb())
    }
}
