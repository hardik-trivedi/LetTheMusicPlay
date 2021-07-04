package com.hardiktrivedi.letthemusicplay.util

import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Extension function to change Long to Kwid, Million and Billion.
 */
fun Long.toCountable(): String {
    val postfix = arrayOf("K", "M", "B")
    val size: Int = java.lang.String.valueOf(this).length
    return when {
        size in 4..6 -> {
            val value = 10.0.pow(1.0).toInt()
            ((this / 1000.0 * value).roundToInt().toDouble() / value).toString() + " " + postfix[0]
        }
        size in 7..9 -> {
            val value = 10.0.pow(1.0).toInt()
            ((this / 1000000.0 * value).roundToInt().toDouble() / value).toString() + " " + postfix[1]
        }
        size >= 10 -> {
            val value = 10.0.pow(1.0).toInt()
            ((this / 1000000000.0 * value).roundToInt().toDouble() / value).toString() + " " + postfix[2]
        }
        else -> {
            this.toString() + ""
        }
    }
}
