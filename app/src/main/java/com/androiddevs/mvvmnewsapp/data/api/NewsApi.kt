package com.androiddevs.mvvmnewsapp.data.api

import com.androiddevs.mvvmnewsapp.data.api.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val PAGE_SIZE = 20

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("pageSize")
        pageSize: Int = PAGE_SIZE,
        @Query("page")
        pageNumber: Int = 1
    ) : NewsResponse

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1
    ) : NewsResponse
}
