package com.androiddevs.mvvmnewsapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.api.PAGE_SIZE
import com.androiddevs.mvvmnewsapp.data.api.model.Article
import com.androiddevs.mvvmnewsapp.data.paging.ArticlePagingSource
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    fun getBreakingNews(): Flow<PagingData<Article>>
}

@ExperimentalPagingApi
class NewsRepoImpl(private val newsApi: NewsApi) : NewsRepo {

    override fun getBreakingNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            remoteMediator = null,
            pagingSourceFactory = {
                ArticlePagingSource(newsApi)
            }
        ).flow
    }
}
