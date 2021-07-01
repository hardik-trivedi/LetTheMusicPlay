package com.hardiktrivedi.letthemusicplay.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hardiktrivedi.letthemusicplay.data.model.Album
import com.hardiktrivedi.letthemusicplay.databinding.AlbumListItemBinding
import com.squareup.picasso.Picasso

class AlbumListAdapter :
    ListAdapter<Album, AlbumListAdapter.AlbumListViewHolder>(AlbumListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        val binding = AlbumListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AlbumListViewHolder(private val binding: AlbumListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album) {
            with(binding) {
                Picasso.get().load(item.largeAlbumArtUrl).into(albumArtImageView);
                albumNameTextView.text = item.name
                albumArtistTextView.text = item.artist
            }
        }
    }
}

private class AlbumListDiffCallback : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.name == newItem.name && oldItem.artist ==  newItem.artist
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}