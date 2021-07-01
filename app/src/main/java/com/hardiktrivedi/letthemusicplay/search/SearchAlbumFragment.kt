package com.hardiktrivedi.letthemusicplay.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hardiktrivedi.letthemusicplay.R
import dagger.hilt.android.AndroidEntryPoint

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
                return false
            }

            override fun onQueryTextChange(newSearch: String?): Boolean {
                viewModel.performSearch(newSearch)
                return true
            }
        }
        )
    }
}