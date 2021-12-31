package com.androiddevs.mvvmnewsapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.api.PAGE_SIZE
import com.androiddevs.mvvmnewsapp.data.api.model.Article
import com.androiddevs.mvvmnewsapp.data.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.data.paging.ArticlePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface NewsRepo {

    fun getBreakingNews(): Flow<PagingData<Article>>

    suspend fun saveArticle(article: com.androiddevs.mvvmnewsapp.data.db.model.Article): Long

    fun getSavedNews() : Flow<List<com.androiddevs.mvvmnewsapp.data.db.model.Article>>
}

@ExperimentalPagingApi
@Singleton
class NewsRepoImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val db: ArticleDatabase
) : NewsRepo {

    override fun getBreakingNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            remoteMediator = null,
            pagingSourceFactory = {
                ArticlePagingSource(newsApi)
            }
        ).flow
    }

    override suspend fun saveArticle(article: com.androiddevs.mvvmnewsapp.data.db.model.Article) =
        db.articleDao().upsert(article)

    override fun getSavedNews(): Flow<List<com.androiddevs.mvvmnewsapp.data.db.model.Article>> =
        db.articleDao().getAllArticles()
}
