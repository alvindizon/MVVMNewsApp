package com.androiddevs.mvvmnewsapp.ui.savednews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.androiddevs.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.androiddevs.mvvmnewsapp.ui.Article
import com.androiddevs.mvvmnewsapp.ui.ArticleDiff
import com.androiddevs.mvvmnewsapp.ui.ArticleViewHolder

class SavedNewsAdapter(private val listener: (Article) -> Unit) :
    ListAdapter<Article, ArticleViewHolder>(ArticleDiff()) {

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
