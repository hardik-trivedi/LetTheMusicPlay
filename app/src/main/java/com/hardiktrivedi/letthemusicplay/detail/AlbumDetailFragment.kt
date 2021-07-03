package com.hardiktrivedi.letthemusicplay.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hardiktrivedi.letthemusicplay.R
import com.hardiktrivedi.letthemusicplay.databinding.AlbumDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : Fragment(R.layout.album_detail_fragment) {

    private lateinit var viewModel: AlbumDetailViewModel

    companion object {
        fun newInstance() = AlbumDetailFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AlbumDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = AlbumDetailFragmentBinding.bind(view)

    }
}