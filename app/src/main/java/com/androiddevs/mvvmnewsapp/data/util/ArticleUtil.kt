package com.androiddevs.mvvmnewsapp.data.util

import com.androiddevs.mvvmnewsapp.data.api.model.Article
import com.androiddevs.mvvmnewsapp.data.api.model.Source

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

fun com.androiddevs.mvvmnewsapp.data.db.model.Article.toDb(): Article {
    return Article(
        author, content, description, publishedAt, Source(source, source), title, url, urlToImage
    )
}
