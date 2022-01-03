package com.androiddevs.mvvmnewsapp.ui.lists

import androidx.recyclerview.widget.DiffUtil
import com.androiddevs.mvvmnewsapp.ui.model.Article

class ArticleDiff : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
