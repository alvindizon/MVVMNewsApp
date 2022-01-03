package com.androiddevs.mvvmnewsapp.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.androiddevs.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.androiddevs.mvvmnewsapp.ui.lists.ArticleDiff
import com.androiddevs.mvvmnewsapp.ui.lists.ArticleViewHolder
import com.androiddevs.mvvmnewsapp.ui.model.Article

class NewsAdapter(private val listener: (Article) -> Unit) :
    PagingDataAdapter<Article, ArticleViewHolder>(ArticleDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemArticlePreviewBinding.inflate(layoutInflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item) {
                listener.invoke(it)
            }
        }
    }
}
