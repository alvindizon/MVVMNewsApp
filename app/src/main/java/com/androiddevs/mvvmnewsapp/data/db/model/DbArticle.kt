package com.androiddevs.mvvmnewsapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "articles", primaryKeys = ["url"])
data class DbArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable
