package com.hardiktrivedi.letthemusicplay.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Extension function to display SnackBar for any view
 */
fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).show()
}