package com.androiddevs.mvvmnewsapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.api.model.ApiArticle

class ArticlePagingSource(private val newsApi: NewsApi, private val searchQuery: String? = null) :
    PagingSource<Int, ApiArticle>() {

    override fun getRefreshKey(state: PagingState<Int, ApiArticle>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiArticle> {
        return try {
            val nextPageNumber = params.key ?: 1

            val response = searchQuery?.let {
                newsApi.searchForNews(searchQuery = it, pageNumber = nextPageNumber)
            } ?: newsApi.getBreakingNews(pageNumber = nextPageNumber)

            LoadResult.Page(
                data = response.articles,
                prevKey = null,
                nextKey = if (nextPageNumber == response.totalResults) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
