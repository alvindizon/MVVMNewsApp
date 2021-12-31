package com.androiddevs.mvvmnewsapp.ui.savednews

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.data.util.toDb
import com.androiddevs.mvvmnewsapp.databinding.FragmentSavedNewsBinding
import com.androiddevs.mvvmnewsapp.ui.NewsAdapter
import com.androiddevs.mvvmnewsapp.ui.utils.setLoadStateListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private val viewModel: SavedNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val binding = FragmentSavedNewsBinding.bind(view)

        val adapter = NewsAdapter {
            val bundle = bundleOf("article" to it.toDb())
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }

        binding.apply {
            rvSavedNews.adapter = adapter
            adapter.setLoadStateListener (
                isNotLoading = { rvSavedNews.isVisible = it },
                isLoading = { progressBar.isVisible = it },
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
