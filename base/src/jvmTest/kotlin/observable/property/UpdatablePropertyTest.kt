package jetbrains.datalore.base.observable.property

import jetbrains.datalore.base.observable.event.EventHandler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UpdatablePropertyTest {
    private var value: String? = null
    private var property: UpdatableProperty<String?>? = null

    @Before
    fun init() {
        property = object : UpdatableProperty<String?>() {
            override fun doGet(): String? {
                return value
            }

            override fun doAddListeners() {}

            override fun doRemoveListeners() {}
        }
    }

    @Test
    fun simpleGet() {
        value = "z"

        assertEquals("z", property!!.get())
    }

    @Test
    fun getWithListenersDoesntGetWithoutUpdate() {
        value = "a"
        val handler = Mockito.mock(EventHandler::class.java)
        property!!.addHandler(handler as EventHandler<PropertyChangeEvent<String?>>)
        value = "b"

        assertEquals("a", property!!.get())
    }

    @Test
    fun updateFiresEvent() {
        val handler = Mockito.mock(EventHandler::class.java)
        property!!.addHandler(handler as EventHandler<PropertyChangeEvent<String?>>)
        value = "z"

        property!!.update()

        Mockito.verify(handler).onEvent(PropertyChangeEvent(null, "z"))
    }

    @Test
    fun updateWithoutChangeDoesntFireEvent() {
        val handler = Mockito.mock(EventHandler::class.java)
        property!!.addHandler(handler as EventHandler<PropertyChangeEvent<String?>>)

        property!!.update()

        Mockito.verifyNoMoreInteractions(handler)

    }

    @Test
    fun removeAllListenersReturnsToSimpleMode() {
        val handler = Mockito.mock(EventHandler::class.java)
        property!!.addHandler(handler as EventHandler<PropertyChangeEvent<String?>>).remove()

        value = "c"

        assertEquals("c", property!!.get())
    }

}