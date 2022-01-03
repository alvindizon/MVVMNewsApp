package com.androiddevs.mvvmnewsapp.domain

import androidx.paging.PagingData
import com.androiddevs.mvvmnewsapp.data.api.model.ApiArticle
import com.androiddevs.mvvmnewsapp.data.db.model.DbArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    fun getBreakingNews(): Flow<PagingData<ApiArticle>>

    suspend fun saveArticle(article: DbArticle): Long

    fun getSavedNews(): Flow<List<DbArticle>>

    fun searchNews(searchQuery: String): Flow<PagingData<ApiArticle>>

    suspend fun deleteArticleByUrl(articleUrl: String)
}

