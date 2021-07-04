package com.hardiktrivedi.letthemusicplay.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hardiktrivedi.letthemusicplay.data.model.Track
import com.hardiktrivedi.letthemusicplay.databinding.TrackListItemBinding
import com.hardiktrivedi.letthemusicplay.util.toSongDuration

class TrackListAdapter :
    ListAdapter<Track, TrackListAdapter.TrackListViewHolder>(TrackListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListViewHolder {
        val binding = TrackListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TrackListViewHolder(private val binding: TrackListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Track) {
            with(binding) {
                titleTextView.text = item.name
                artistTextView.text = item.artist.name
                durationTextView.text = item.duration.toSongDuration()
            }
        }
    }
}

/**
 * Diff logic helps RecyclerView to identify the new entiry in adapter
 */
private class TrackListDiffCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.name == newItem.name && oldItem.artist.name == newItem.artist.name
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}