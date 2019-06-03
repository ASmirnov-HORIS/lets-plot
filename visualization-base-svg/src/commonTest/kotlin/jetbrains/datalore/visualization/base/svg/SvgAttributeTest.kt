package jetbrains.datalore.visualization.base.svg

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SvgAttributeTest {
    private var element: SvgEllipseElement? = null

    @BeforeTest
    fun setUp() {
        element = SvgEllipseElement(10.0, 20.0, 30.0, 40.0)
    }

    private fun elem() = element as SvgEllipseElement

    @Test
    fun testSetAttr() {
        elem().cx().set(100.0)
        elem().getAttribute("fill").set("yellow")
        elem().setAttribute("stroke", "black")

        assertEquals(100.0, elem().cx().get())
        assertEquals("yellow", elem().getAttribute("fill").get())
        assertEquals("black", elem().getAttribute("stroke").get())
    }

    @Test
    fun testNotSetAttr() {
        assertNull(elem().getAttribute("fill").get())
    }

    @Test
    fun testResetAttr() {
        elem().setAttribute("fill", "yellow")
        elem().getAttribute("fill").set("red")

        assertEquals("red", elem().getAttribute("fill").get())
    }
}