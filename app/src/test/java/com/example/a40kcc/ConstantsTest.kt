package com.example.a40kcc

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ConstantsTest {

    @Test
    fun colorsMapContainsExpectedEntries() {
        assertEquals(14, COLORS.size)
        assertEquals(0xff0000ff, COLORS["Blue"])
        assertEquals(0xff000000, COLORS["Black"])
        assertTrue(COLORS.containsKey("White"))
    }
}

