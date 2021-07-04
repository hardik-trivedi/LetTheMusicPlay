package com.hardiktrivedi.letthemusicplay.util

import android.widget.ImageView
import com.hardiktrivedi.letthemusicplay.R
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String, placeHolder: Int = R.drawable.ic_album_placeholder) {
    Picasso.get()
        .load(url)
        .placeholder(placeHolder)
        .into(this)
}