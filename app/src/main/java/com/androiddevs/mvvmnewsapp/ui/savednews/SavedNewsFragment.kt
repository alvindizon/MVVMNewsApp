package com.androiddevs.mvvmnewsapp.ui.savednews

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.FragmentSavedNewsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private val viewModel: SavedNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val binding = FragmentSavedNewsBinding.bind(view)

        val adapter = SavedNewsAdapter {
            val bundle = bundleOf("article" to it)
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // if true then it means item changed position--for our usecase we'll just be deleting items so no need to indicate
                // that item has been moved
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                adapter.currentList[position]?.let { article ->
                    viewModel.onItemSwipe(article)
                }
            }
        }
        ).attachToRecyclerView(binding.rvSavedNews)

        binding.rvSavedNews.adapter = adapter

        viewLifecycleOwner.apply {
            viewModel.onViewCreated().flowWithLifecycle(lifecycle)
                .onEach { data -> adapter.submitList(data) }
                .launchIn(lifecycleScope)

            viewModel.savedNewsEvent.flowWithLifecycle(lifecycle)
                .onEach { event ->
                    when (event) {
                        is SavedNewsViewModel.SavedNewsEvent.ShowDeleteSuccessSnackbar -> {
                            Snackbar.make(
                                view,
                                "Successfully deleted article",
                                Snackbar.LENGTH_LONG
                            ).apply {
                                setAction("Undo") {
                                    viewModel.onUndoClick(event.article)
                                }
                                show()
                            }

                        }
                    }
                }
                .launchIn(lifecycleScope)
        }
// alternative way:
//        lifecycleScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    viewModel.onViewCreated().collect { data -> adapter.submitList(data) }
//                }
//
//                launch {
//                    viewModel.savedNewsEvent.collect { event ->
//                        when (event) {
//                            is SavedNewsViewModel.SavedNewsEvent.ShowDeleteSuccessSnackbar -> {
//                                Snackbar.make(
//                                    view,
//                                    "Successfully deleted article",
//                                    Snackbar.LENGTH_LONG
//                                ).apply {
//                                    setAction("Undo") {
//                                        viewModel.onUndoClick(event.article)
//                                    }
//                                    show()
//                                }
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

}
