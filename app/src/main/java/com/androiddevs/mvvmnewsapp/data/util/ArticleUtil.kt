package com.androiddevs.mvvmnewsapp.data.util

import com.androiddevs.mvvmnewsapp.data.api.model.ApiArticle
import com.androiddevs.mvvmnewsapp.data.db.model.DbArticle
import com.androiddevs.mvvmnewsapp.ui.model.Article

fun Article.toDb(): DbArticle {
    return DbArticle(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun DbArticle.toUi(): Article {
    return Article(
        author, content, description, publishedAt, source, title, url, urlToImage
    )
}

fun ApiArticle.toUi(): Article {
    return Article(
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
