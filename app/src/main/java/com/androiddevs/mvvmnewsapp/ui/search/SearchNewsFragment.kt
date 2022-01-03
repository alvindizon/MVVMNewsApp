package com.androiddevs.mvvmnewsapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.FragmentSearchNewsBinding
import com.androiddevs.mvvmnewsapp.ui.paging.NewsAdapter
import com.androiddevs.mvvmnewsapp.ui.utils.setLoadStateListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
@FlowPreview
class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private val viewModel: SearchNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentSearchNewsBinding.bind(view)

        val adapter = NewsAdapter {
            val bundle = bundleOf("article" to it)
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }

        binding.apply {
            rvSearchNews.adapter = adapter
            adapter.setLoadStateListener(
                isNotLoading = { rvSearchNews.isVisible = it },
                isLoading = { paginationProgressBar.isVisible = it },
                errorListener = {
                    Snackbar.make(
                        requireView(),
                        "Error: ${it.message}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            )

            etSearch.addTextChangedListener { editable ->
                if (!editable.isNullOrEmpty()) {
                    viewModel.searchQuery.value = editable.toString()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onQuerySearched.collectLatest { data -> adapter.submitData(data) }
        }
    }

}
