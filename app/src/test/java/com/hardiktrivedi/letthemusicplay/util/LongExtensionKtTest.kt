package com.hardiktrivedi.letthemusicplay.util

import org.junit.Assert.assertEquals
import org.junit.Test

class LongExtensionKtTest {
    @Test
    fun `play count is converted into countable in thousands`() {
        assertEquals("1.0 K", 1000L.toCountable())
    }

    @Test
    fun `play count is converted into countable in ten thousands`() {
        assertEquals("47.6 K", 47602L.toCountable())
    }

    @Test
    fun `play count is converted into countable in hundred thousands`() {
        assertEquals("213.0 K", 212991L.toCountable())
    }

    @Test
    fun `play count is converted into countable in million`() {
        assertEquals("10.0 M", 10_000_000L.toCountable())
    }

    @Test
    fun `play count is converted into countable in billion`() {
        assertEquals("5.0 B", 5_000_000_000L.toCountable())
    }
}