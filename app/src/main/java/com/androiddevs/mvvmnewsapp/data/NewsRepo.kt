package com.androiddevs.mvvmnewsapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.api.PAGE_SIZE
import com.androiddevs.mvvmnewsapp.data.api.model.ApiArticle
import com.androiddevs.mvvmnewsapp.data.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.data.db.model.DbArticle
import com.androiddevs.mvvmnewsapp.data.paging.ArticlePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface NewsRepo {

    fun getBreakingNews(): Flow<PagingData<ApiArticle>>

    suspend fun saveArticle(article: DbArticle): Long

    fun getSavedNews(): Flow<List<DbArticle>>

    fun searchNews(searchQuery: String): Flow<PagingData<ApiArticle>>

    suspend fun deleteArticle(article: DbArticle)
}

@ExperimentalPagingApi
@Singleton
class NewsRepoImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val db: ArticleDatabase
) : NewsRepo {

    override fun getBreakingNews(): Flow<PagingData<ApiArticle>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            remoteMediator = null,
            pagingSourceFactory = {
                ArticlePagingSource(newsApi)
            }
        ).flow
    }

    override suspend fun saveArticle(article: DbArticle) =
        db.articleDao().upsert(article)

    override fun getSavedNews(): Flow<List<DbArticle>> =
        db.articleDao().getAllArticles()

    override fun searchNews(searchQuery: String): Flow<PagingData<ApiArticle>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            remoteMediator = null,
            pagingSourceFactory = {
                ArticlePagingSource(newsApi, searchQuery)
            }
        ).flow
    }

    override suspend fun deleteArticle(article: DbArticle) =
        db.articleDao().deleteArticle(article)
}
