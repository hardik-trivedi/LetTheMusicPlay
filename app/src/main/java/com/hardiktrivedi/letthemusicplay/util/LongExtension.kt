package com.hardiktrivedi.letthemusicplay.util

fun Long.toCountable(): String {
    val postfix = arrayOf("K", "M", "B")
    val size: Int = java.lang.String.valueOf(this).length
    return if (size >= 4 && size < 7) {
        val value = Math.pow(10.0, 1.0).toInt()
        (Math.round(this / 1000.0 * value).toDouble() / value).toString() + " " + postfix.get(0)
    } else if (size > 6 && size < 10) {
        val value = Math.pow(10.0, 1.0).toInt()
        (Math.round(this / 1000000.0 * value).toDouble() / value).toString() + " " + postfix.get(1)
    } else if (size >= 10) {
        val value = Math.pow(10.0, 1.0).toInt()
        (Math.round(this / 1000000000.0 * value).toDouble() / value).toString() + " " + postfix.get(2)
    } else {
        this.toString() + ""
    }
}
