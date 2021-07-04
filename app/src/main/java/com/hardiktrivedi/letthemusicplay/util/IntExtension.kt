package com.hardiktrivedi.letthemusicplay.util

/**
 * Extension function to change a number to track duration.
 */
fun Int.toSongDuration(): String {
    val hours = this / 3600
    val minutes = this % 3600 / 60
    val seconds = this % 60

    return when {
        hours > 0 -> {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
        else -> {
            String.format("%02d:%02d", minutes, seconds)
        }
    }
}
