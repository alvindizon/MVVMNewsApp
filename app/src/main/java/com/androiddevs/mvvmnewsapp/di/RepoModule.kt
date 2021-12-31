package com.androiddevs.mvvmnewsapp.di

import androidx.paging.ExperimentalPagingApi
import com.androiddevs.mvvmnewsapp.data.NewsRepo
import com.androiddevs.mvvmnewsapp.data.NewsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @ExperimentalPagingApi
    @Binds
    abstract fun bindNewsRepo(newsRepo: NewsRepoImpl): NewsRepo
}
