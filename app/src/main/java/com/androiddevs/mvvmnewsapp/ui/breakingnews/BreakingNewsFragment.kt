package com.androiddevs.mvvmnewsapp.ui.breakingnews

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.FragmentBreakingNewsBinding
import com.androiddevs.mvvmnewsapp.ui.paging.NewsAdapter
import com.androiddevs.mvvmnewsapp.ui.utils.setLoadStateListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private val viewModel: BreakingNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val binding = FragmentBreakingNewsBinding.bind(view)

        val adapter = NewsAdapter {
            val bundle = bundleOf("article" to it)
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        binding.apply {
            rvBreakingNews.adapter = adapter
            adapter.setLoadStateListener (
                isNotLoading = { rvBreakingNews.isVisible = it },
                isLoading = { paginationProgressBar.isVisible = it },
                errorListener = {
                    Snackbar.make(
                        requireView(),
                        "Error: ${it.message}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            )
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onViewCreated().collectLatest { data -> adapter.submitData(data) }
        }
    }
}
