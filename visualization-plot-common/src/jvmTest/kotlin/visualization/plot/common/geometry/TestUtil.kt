package jetbrains.datalore.visualization.plot.common.geometry

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.visualization.plot.gog.common.geometry.Utils.calculateArea
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.assertj.core.api.Condition
import java.util.*

object TestUtil {

    internal val COMPLEX_DATA = listOf(
            DoubleVector(126.73828125, 34.4522184728265),
            DoubleVector(126.73828125, 34.307143856288),
            DoubleVector(126.5625, 34.307143856288),
            DoubleVector(126.38671875, 34.307143856288),
            DoubleVector(126.2109375, 34.307143856288),
            DoubleVector(126.03515625, 34.307143856288),
            DoubleVector(126.03515625, 34.4522184728265),
            DoubleVector(126.2109375, 34.4522184728265),
            DoubleVector(126.2109375, 34.5970415161442),
            DoubleVector(126.03515625, 34.5970415161442),
            DoubleVector(125.859375, 34.5970415161442),
            DoubleVector(125.859375, 34.7416124988317),
            DoubleVector(126.03515625, 34.7416124988317),
            DoubleVector(126.03515625, 34.8859309407531),
            DoubleVector(126.2109375, 34.8859309407531),
            DoubleVector(126.2109375, 35.0299963690257),
            DoubleVector(126.03515625, 35.0299963690257),
            DoubleVector(126.03515625, 35.1738083179996),
            DoubleVector(126.2109375, 35.1738083179996),
            DoubleVector(126.38671875, 35.1738083179996),
            DoubleVector(126.38671875, 35.3173663292379),
            DoubleVector(126.38671875, 35.4606699514953),
            DoubleVector(126.5625, 35.4606699514953),
            DoubleVector(126.5625, 35.6037187406973),
            DoubleVector(126.5625, 35.7465122599185),
            DoubleVector(126.73828125, 35.7465122599185),
            DoubleVector(126.73828125, 35.8890500793609),
            DoubleVector(126.5625, 35.8890500793609),
            DoubleVector(126.5625, 36.0313317763319),
            DoubleVector(126.5625, 36.1733569352216),
            DoubleVector(126.5625, 36.3151251474805),
            DoubleVector(126.5625, 36.4566360115962),
            DoubleVector(126.5625, 36.5978891330702),
            DoubleVector(126.38671875, 36.5978891330702),
            DoubleVector(126.2109375, 36.5978891330702),
            DoubleVector(126.2109375, 36.7388841243943),
            DoubleVector(126.2109375, 36.8796206050268),
            DoubleVector(126.2109375, 37.0200982013681),
            DoubleVector(126.38671875, 37.0200982013681),
            DoubleVector(126.5625, 37.0200982013681),
            DoubleVector(126.73828125, 37.0200982013681),
            DoubleVector(126.73828125, 37.1603165467368),
            DoubleVector(126.5625, 37.1603165467368),
            DoubleVector(126.5625, 37.3002752813443),
            DoubleVector(126.73828125, 37.3002752813443),
            DoubleVector(126.5625, 37.4399740522706),
            DoubleVector(126.5625, 37.5794125134384),
            DoubleVector(126.38671875, 37.5794125134384),
            DoubleVector(126.38671875, 37.7185903255881),
            DoubleVector(126.2109375, 37.7185903255881),
            DoubleVector(126.2109375, 37.857507156252),
            DoubleVector(126.38671875, 37.857507156252),
            DoubleVector(126.5625, 37.7185903255881),
            DoubleVector(126.73828125, 37.7185903255881),
            DoubleVector(126.73828125, 37.857507156252),
            DoubleVector(126.73828125, 37.9961626797281),
            DoubleVector(126.9140625, 37.9961626797281),
            DoubleVector(126.9140625, 38.1345565770541),
            DoubleVector(126.9140625, 38.272688535981),
            DoubleVector(127.08984375, 38.272688535981),
            DoubleVector(127.265625, 38.272688535981),
            DoubleVector(127.44140625, 38.272688535981),
            DoubleVector(127.6171875, 38.272688535981),
            DoubleVector(127.79296875, 38.272688535981),
            DoubleVector(127.96875, 38.272688535981),
            DoubleVector(128.14453125, 38.272688535981),
            DoubleVector(128.14453125, 38.4105582509461),
            DoubleVector(128.3203125, 38.4105582509461),
            DoubleVector(128.3203125, 38.5481654230466),
            DoubleVector(128.49609375, 38.5481654230466),
            DoubleVector(128.49609375, 38.4105582509461),
            DoubleVector(128.49609375, 38.272688535981),
            DoubleVector(128.671875, 38.272688535981),
            DoubleVector(128.671875, 38.1345565770541),
            DoubleVector(128.671875, 37.9961626797281),
            DoubleVector(128.84765625, 37.9961626797281),
            DoubleVector(128.84765625, 37.857507156252),
            DoubleVector(129.0234375, 37.7185903255881),
            DoubleVector(129.0234375, 37.5794125134384),
            DoubleVector(129.19921875, 37.5794125134384),
            DoubleVector(129.19921875, 37.4399740522706),
            DoubleVector(129.19921875, 37.3002752813443),
            DoubleVector(129.375, 37.3002752813443),
            DoubleVector(129.375, 37.1603165467368),
            DoubleVector(129.375, 37.0200982013681),
            DoubleVector(129.375, 36.8796206050268),
            DoubleVector(129.375, 36.7388841243943),
            DoubleVector(129.375, 36.5978891330702),
            DoubleVector(129.375, 36.4566360115962),
            DoubleVector(129.375, 36.3151251474805),
            DoubleVector(129.375, 36.1733569352216),
            DoubleVector(129.375, 36.0313317763319),
            DoubleVector(129.55078125, 36.0313317763319),
            DoubleVector(129.55078125, 35.8890500793609),
            DoubleVector(129.55078125, 35.7465122599185),
            DoubleVector(129.55078125, 35.6037187406973),
            DoubleVector(129.375, 35.6037187406973),
            DoubleVector(129.375, 35.4606699514953),
            DoubleVector(129.375, 35.3173663292379),
            DoubleVector(129.19921875, 35.3173663292379),
            DoubleVector(129.19921875, 35.1738083179996),
            DoubleVector(129.0234375, 35.1738083179996),
            DoubleVector(128.84765625, 35.1738083179996),
            DoubleVector(128.84765625, 35.0299963690257),
            DoubleVector(128.671875, 35.0299963690257),
            DoubleVector(128.671875, 34.8859309407531),
            DoubleVector(128.671875, 34.7416124988317),
            DoubleVector(128.49609375, 34.7416124988317),
            DoubleVector(128.3203125, 34.7416124988317),
            DoubleVector(128.14453125, 34.7416124988317),
            DoubleVector(127.96875, 34.7416124988317),
            DoubleVector(127.79296875, 34.7416124988317),
            DoubleVector(127.6171875, 34.7416124988317),
            DoubleVector(127.44140625, 34.7416124988317),
            DoubleVector(127.44140625, 34.5970415161442),
            DoubleVector(127.44140625, 34.4522184728265),
            DoubleVector(127.265625, 34.4522184728265),
            DoubleVector(127.265625, 34.5970415161442),
            DoubleVector(127.08984375, 34.5970415161442),
            DoubleVector(127.08984375, 34.4522184728265),
            DoubleVector(127.08984375, 34.307143856288),
            DoubleVector(126.9140625, 34.307143856288),
            DoubleVector(126.73828125, 34.4522184728265)
    )

    internal val MEDIUM_DATA = listOf(
            DoubleVector(16.5234375, 46.8000594467873),
            DoubleVector(16.171875, 46.8000594467873),
            DoubleVector(16.171875, 47.0401821448067),
            DoubleVector(16.5234375, 47.0401821448067),
            DoubleVector(16.5234375, 47.2792290025708),
            DoubleVector(16.5234375, 47.5172006978394),
            DoubleVector(16.5234375, 47.75409797968),
            DoubleVector(16.875, 47.75409797968),
            DoubleVector(17.2265625, 47.9899216674142),
            DoubleVector(17.578125, 47.9899216674142),
            DoubleVector(17.578125, 47.75409797968),
            DoubleVector(17.9296875, 47.75409797968),
            DoubleVector(18.28125, 47.75409797968),
            DoubleVector(18.6328125, 47.75409797968),
            DoubleVector(18.6328125, 47.9899216674142),
            DoubleVector(18.984375, 47.9899216674142),
            DoubleVector(19.3359375, 47.9899216674142),
            DoubleVector(19.3359375, 48.2246726495652),
            DoubleVector(19.6875, 48.2246726495652),
            DoubleVector(20.0390625, 48.2246726495652),
            DoubleVector(20.390625, 48.2246726495652),
            DoubleVector(20.390625, 48.4583518828086),
            DoubleVector(20.7421875, 48.4583518828086),
            DoubleVector(21.09375, 48.4583518828086),
            DoubleVector(21.4453125, 48.4583518828086),
            DoubleVector(21.796875, 48.4583518828086),
            DoubleVector(22.1484375, 48.4583518828086),
            DoubleVector(22.1484375, 48.2246726495652),
            DoubleVector(22.5, 48.2246726495652),
            DoubleVector(22.5, 47.9899216674142),
            DoubleVector(22.8515625, 47.9899216674142),
            DoubleVector(22.8515625, 47.75409797968),
            DoubleVector(22.5, 47.75409797968),
            DoubleVector(22.1484375, 47.75409797968),
            DoubleVector(22.1484375, 47.5172006978394),
            DoubleVector(22.1484375, 47.2792290025708),
            DoubleVector(21.796875, 47.2792290025708),
            DoubleVector(21.796875, 47.0401821448067),
            DoubleVector(21.4453125, 47.0401821448067),
            DoubleVector(21.4453125, 46.8000594467873),
            DoubleVector(21.4453125, 46.5588603031172),
            DoubleVector(21.4453125, 46.3165841818222),
            DoubleVector(21.09375, 46.3165841818222),
            DoubleVector(20.7421875, 46.3165841818222),
            DoubleVector(20.7421875, 46.0732306254083),
            DoubleVector(20.390625, 46.0732306254083),
            DoubleVector(20.0390625, 46.0732306254083),
            DoubleVector(19.6875, 46.0732306254083),
            DoubleVector(19.3359375, 46.0732306254083),
            DoubleVector(18.984375, 46.0732306254083),
            DoubleVector(18.984375, 45.8287992519213),
            DoubleVector(18.6328125, 45.8287992519213),
            DoubleVector(18.28125, 45.8287992519213),
            DoubleVector(17.9296875, 45.8287992519213),
            DoubleVector(17.578125, 45.8287992519213),
            DoubleVector(17.2265625, 45.8287992519213),
            DoubleVector(17.2265625, 46.0732306254083),
            DoubleVector(17.2265625, 46.3165841818222),
            DoubleVector(16.875, 46.3165841818222),
            DoubleVector(16.5234375, 46.3165841818222),
            DoubleVector(16.5234375, 46.5588603031172),
            DoubleVector(16.5234375, 46.8000594467873)
    )


    internal val SIMPLE_DATA = listOf(
            DoubleVector(-82.265625, 82.2616987368315),
            DoubleVector(-82.265625, 82.2142171410677),
            DoubleVector(-82.265625, 82.1664460084773),
            DoubleVector(-81.9140625, 82.1664460084773),
            DoubleVector(-81.9140625, 82.1183836069127),
            DoubleVector(-81.5625, 82.1183836069127),
            DoubleVector(-81.2109375, 82.1183836069127),
            DoubleVector(-81.2109375, 82.0700281944827),
            DoubleVector(-80.859375, 82.0700281944827),
            DoubleVector(-80.859375, 82.1183836069127),
            DoubleVector(-80.859375, 82.1664460084773),
            DoubleVector(-81.2109375, 82.1664460084773),
            DoubleVector(-81.5625, 82.1664460084773),
            DoubleVector(-81.5625, 82.2142171410677),
            DoubleVector(-81.9140625, 82.2142171410677),
            DoubleVector(-81.9140625, 82.2616987368315),
            DoubleVector(-82.265625, 82.2616987368315)
    )


    fun createCircle(pointsCount: Int, r: Double): List<DoubleVector> {
        @Suppress("NAME_SHADOWING")
        var pointsCount = pointsCount
        val circle = ArrayList<DoubleVector>()
        val step = 2 * Math.PI / pointsCount++
        var angle = 0.0
        while (pointsCount-- > 0) {
            circle.add(DoubleVector(r * Math.cos(angle), r * Math.sin(angle)))
            angle += step
        }

        circle[circle.size - 1] = circle[0]

        return circle
    }

    fun getPointsCount(rings: List<List<DoubleVector>>): Int {
        return rings.stream().mapToInt { it.size }.sum()
    }

    class RingAssertion internal constructor(ring: List<DoubleVector>) : AbstractAssert<RingAssertion, List<DoubleVector>>(ring, RingAssertion::class.java) {

        val isClosed: RingAssertion
            get() {
                assertThat(actual.get(0)).isEqualTo(actual.get(actual.size - 1))
                return this
            }

        fun hasSize(expected: Int): RingAssertion {
            assertThat(actual).hasSize(expected)
            return this
        }

        fun hasArea(expected: Double): RingAssertion {
            return hasArea(expected, 0.001)
        }

        internal fun hasArea(expected: Double, epsilon: Double): RingAssertion {
            assertThat(calculateArea(actual)).isEqualTo(expected, offset(epsilon))
            return this
        }

        companion object {

            fun assertThatRing(ring: List<DoubleVector>): RingAssertion {
                return RingAssertion(ring)
            }
        }
    }

    class ValidRingCondition<T> internal constructor(private val myData: List<T>) : Condition<List<Int>>() {

        override fun matches(value: List<Int>): Boolean {
            return myData[value[0]] == myData[value[value.size - 1]]
        }
    }
}
