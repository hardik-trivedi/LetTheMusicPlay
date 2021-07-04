package com.hardiktrivedi.letthemusicplay.util

import org.junit.Assert.assertEquals
import org.junit.Test

class IntExtensionKtTest {
    @Test
    fun `number is converted to duration in seconds`() {
        assertEquals("00:28", 28.toSongDuration())
    }

    @Test
    fun `number is converted to duration in minutes and seconds`() {
        assertEquals("03:48", 228.toSongDuration())
    }

    @Test
    fun `number is converted to duration in hours minutes and seconds`() {
        assertEquals("01:27:08", 5228.toSongDuration())
    }
}