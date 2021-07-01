package com.hardiktrivedi.letthemusicplay.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hardiktrivedi.letthemusicplay.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchAlbumFragment : Fragment(R.layout.search_album_fragment) {

    private lateinit var viewModel: SearchAlbumViewModel

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

    private fun setUpSearchView(menuItem: MenuItem) {
        (menuItem.actionView as? SearchView)?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newSearch: String?): Boolean {
                performSearch(newSearch)
                return true
            }
        }
        )
    }

    private fun performSearch(newSearch: String?) {
        lifecycleScope.launch {
            viewModel.performSearch(newSearch).collect {

            }
        }
    }
}