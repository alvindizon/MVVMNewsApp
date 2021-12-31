package com.androiddevs.mvvmnewsapp.ui.article

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.FragmentArticleBinding
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentArticleBinding.bind(view)
        binding.webView.apply {
            webViewClient = WebViewClient()
            args.article.url?.let { loadUrl(it) } ?: Snackbar.make(
                requireView(),
                "Invalid URL",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        binding.fab.setOnClickListener {
            //TODO actually save the article to DB
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}
