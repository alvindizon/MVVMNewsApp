package com.androiddevs.mvvmnewsapp.data.db

import androidx.room.*
import com.androiddevs.mvvmnewsapp.data.db.model.DbArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: DbArticle): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles() : Flow<List<DbArticle>>

    @Query("DELETE FROM articles WHERE url = :articleUrl")
    suspend fun deleteArticle(articleUrl: String)
}
