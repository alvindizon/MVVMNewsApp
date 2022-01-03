package com.androiddevs.mvvmnewsapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.bumptech.glide.Glide

class ArticleViewHolder(
    private val binding: ItemArticlePreviewBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, listener: (Article) -> Unit) {
        binding.apply {
            Glide.with(binding.ivArticleImage).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
        }

        itemView.setOnClickListener { listener.invoke(article) }
    }
}
