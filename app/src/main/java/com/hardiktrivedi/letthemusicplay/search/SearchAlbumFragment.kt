package com.hardiktrivedi.letthemusicplay.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hardiktrivedi.letthemusicplay.R
import com.hardiktrivedi.letthemusicplay.databinding.SearchAlbumFragmentBinding
import com.hardiktrivedi.letthemusicplay.util.hideKeyboard
import com.hardiktrivedi.letthemusicplay.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.net.UnknownHostException

/**
 * A fragment which is used to search the album by it's name.
 */
@AndroidEntryPoint
class SearchAlbumFragment : Fragment(R.layout.search_album_fragment) {

    private lateinit var viewModel: SearchAlbumViewModel
    private lateinit var binding: SearchAlbumFragmentBinding
    private val albumListAdapter = AlbumListAdapter()

    companion object {
        fun newInstance() = SearchAlbumFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SearchAlbumViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.album_list_menu, menu)
        setUpSearchView(menu.findItem(R.id.actionSearch))
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SearchAlbumFragmentBinding.bind(view)
        with(binding.albumRecyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = albumListAdapter
        }
    }

    private fun setUpSearchView(menuItem: MenuItem) {
        val searchView = (menuItem.actionView as? SearchView)
        searchView?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                /**
                 * User has completed typing and press the enter or ime button on soft keyboard.
                 * After user taps on such action button keyboard is hidden and search is performed.
                 */
                searchView.hideKeyboard()
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newSearch: String?): Boolean {
                return true
            }
        }
        )
    }

    private fun performSearch(newSearch: String?) {
        lifecycleScope.launch {
            viewModel.performSearch(newSearch)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    when (e) {
                        is UnknownHostException -> binding.searchResultConstraintLayout.showSnackBar(
                            getString(
                                R.string.no_internet_message
                            )
                        )
                        else -> {
                            binding.searchResultConstraintLayout.showSnackBar(
                                getString(
                                    R.string.something_went_wrong
                                )
                            )
                        }
                    }
                }
                .collect {
                    albumListAdapter.submitList(it)
                }
        }
    }
}