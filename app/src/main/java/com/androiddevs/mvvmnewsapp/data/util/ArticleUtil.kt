package com.androiddevs.mvvmnewsapp.data.util

import com.androiddevs.mvvmnewsapp.data.api.model.Article

fun Article.toDb(): com.androiddevs.mvvmnewsapp.data.db.model.Article {
    return com.androiddevs.mvvmnewsapp.data.db.model.Article(
        author = author ?: "",
        content = content ?: "",
        description = description ?: "",
        publishedAt = publishedAt ?: "",
        source = source?.name ?: "",
        title = title ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: ""
    )
}
