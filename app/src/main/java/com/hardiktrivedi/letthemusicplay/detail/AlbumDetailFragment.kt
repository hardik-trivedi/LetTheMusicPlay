package com.hardiktrivedi.letthemusicplay.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardiktrivedi.letthemusicplay.R
import com.hardiktrivedi.letthemusicplay.data.model.AlbumObject
import com.hardiktrivedi.letthemusicplay.databinding.AlbumDetailFragmentBinding
import com.hardiktrivedi.letthemusicplay.util.largeAlbumArtUrl
import com.hardiktrivedi.letthemusicplay.util.loadUrl
import com.hardiktrivedi.letthemusicplay.util.showSnackBar
import com.hardiktrivedi.letthemusicplay.util.toCountable
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.net.UnknownHostException


@AndroidEntryPoint
class AlbumDetailFragment : Fragment(R.layout.album_detail_fragment) {

    private lateinit var viewModel: AlbumDetailViewModel
    private lateinit var binding: AlbumDetailFragmentBinding

    private val trackListAdapter = TrackListAdapter()

    private val args: AlbumDetailFragmentArgs by navArgs()

    companion object {
        fun newInstance() = AlbumDetailFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AlbumDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AlbumDetailFragmentBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(AlbumDetailViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getAlbumDetail(args.album, args.artist)
                .catch { e ->
                    when (e) {
                        is UnknownHostException -> binding.root.showSnackBar(
                            getString(
                                R.string.no_internet_message
                            )
                        )
                        else -> {
                            binding.root.showSnackBar(
                                getString(
                                    R.string.something_went_wrong
                                )
                            )
                        }
                    }
                }
                .collect {
                    showAlbumDetail(it)
                }
        }
    }

    private fun showAlbumDetail(it: AlbumObject) {
        with(binding) {
            albumArtImageView.loadUrl(it.image.largeAlbumArtUrl)
            albumNameTextView.text = it.name
            albumArtistTextView.text = it.artist
            albumPublishedDate.text = it.wiki?.published
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                albumSummaryTextView.text =
                    Html.fromHtml(it.wiki?.summary?:"", Html.FROM_HTML_MODE_COMPACT)
            } else {
                albumSummaryTextView.text = Html.fromHtml(it.wiki?.summary?:"")
            }
            albumSummaryTextView.movementMethod = ScrollingMovementMethod()
            listenerCountTextView.text = it.listeners.toLong().toCountable()
            playCountTextView.text = it.playCount.toLong().toCountable()
            with(trackRecyclerView) {
                layoutManager = LinearLayoutManager(activity)
                adapter = trackListAdapter
            }
            trackListAdapter.submitList(it.tracks?.track)
        }
    }
}