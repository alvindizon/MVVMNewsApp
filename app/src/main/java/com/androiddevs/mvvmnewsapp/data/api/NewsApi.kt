package com.androiddevs.mvvmnewsapp.data.api

import com.androiddevs.mvvmnewsapp.data.api.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("pageSize")
        pageSize: Int = 20,
        @Query("page")
        pageNumber: Int = 1
    ) : NewsResponse
}
