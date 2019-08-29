package jetbrains.datalore.base.numberFormat

import kotlin.test.Test
import kotlin.test.assertEquals

class NumberFormatTypeGTest {
    @Test
    fun generalNotationWithoutPRecision() {
        assertEquals("0.00005", NumberFormat("g").apply(0.00005))
        assertEquals("0.05", NumberFormat("g").apply(0.05))
        assertEquals("1", NumberFormat("g").apply(1.0))
    }

    @Test
    fun canOutputGeneralNotation() {
        assertEquals("0.05", NumberFormat(".1g").apply(0.049))
        assertEquals("0.5", NumberFormat(".1g").apply(0.49))
        assertEquals("0.45", NumberFormat(".2g").apply(0.449))
        assertEquals("0.445", NumberFormat(".3g").apply(0.4449))
        assertEquals("0.44445", NumberFormat(".5g").apply(0.444449))
        assertEquals("1e+2", NumberFormat(".1g").apply(100))
        assertEquals("1.0e+2", NumberFormat(".2g").apply(100))
        assertEquals("100", NumberFormat(".3g").apply(100))
        assertEquals("100.00", NumberFormat(".5g").apply(100))
        assertEquals("100.20", NumberFormat(".5g").apply(100.2))
        assertEquals("0.0020", NumberFormat(".2g").apply(0.002))
    }

    @Test
    fun canGroupThousandsWithGeneralNotation() {
        val f = NumberFormat(",.12g")
        assertEquals("0.00000000000", f.apply(0))
        assertEquals("42.0000000000", f.apply(42))
        assertEquals("42,000,000.0000", f.apply(42000000))
        assertEquals("420,000,000.000", f.apply(420000000))
        assertEquals("-4.00000000000", f.apply(-4))
        assertEquals("-42.0000000000", f.apply(-42))
        assertEquals("-4,200,000.00000", f.apply(-4200000))
        assertEquals("-42,000,000.0000", f.apply(-42000000))
    }
}