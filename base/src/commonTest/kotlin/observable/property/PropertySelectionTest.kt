package jetbrains.datalore.base.observable.property

import jetbrains.datalore.base.function.Function
import jetbrains.datalore.base.observable.event.EventHandler
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PropertySelectionTest {
    private val c2 = C2()
    private val selProp = Properties.select(c2.ref, object : Function<C1?, ReadableProperty<Int?>> {
        override fun apply(value: C1?): ReadableProperty<Int?> {
            return value!!.value
        }
    }, 30)
    private var changed = false

    private fun addListener() {
        selProp.addHandler(object : EventHandler<PropertyChangeEvent<Int?>> {
            override fun onEvent(event: PropertyChangeEvent<Int?>) {
                changed = true
            }
        })
    }

    @Test
    fun initialValue() {
        assertEquals(30, selProp.get() as Int)
    }

    @Test
    fun valueSet() {
        addListener()
        val c1 = C1(239)
        c2.ref.set(c1)
        assertEquals(239, selProp.get() as Int)
        assertTrue(changed)
    }

    @Test
    fun subvalueChange() {
        addListener()
        val c1 = C1(239)
        c2.ref.set(c1)

        changed = false
        c1.value.set(30)
        assertTrue(changed)
    }

    @Test
    fun subvalueChangeListenerAddedAfterRefSet() {
        val c1 = C1(239)
        c2.ref.set(c1)
        addListener()

        changed = false
        c1.value.set(30)
        assertTrue(changed)
    }

    internal class C1(v: Int?) {
        internal val value = ValueProperty<Int?>(null)

        init {
            value.set(v)
        }
    }

    internal class C2 {
        internal val ref = ValueProperty<C1?>(null)
    }
}