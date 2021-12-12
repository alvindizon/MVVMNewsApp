package com.androiddevs.mvvmnewsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androiddevs.mvvmnewsapp.data.db.model.Article

@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}
