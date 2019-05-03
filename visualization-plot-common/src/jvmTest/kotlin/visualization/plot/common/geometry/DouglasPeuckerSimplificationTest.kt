package jetbrains.datalore.visualization.plot.common.geometry

import jetbrains.datalore.visualization.plot.gog.common.geometry.PolylineSimplifier
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DouglasPeuckerSimplificationTest {

    @Test
    fun simplificationByCountShouldNotBreakRing() {

        val indices = PolylineSimplifier.douglasPeucker(TestUtil.SIMPLE_DATA).setCountLimit(4).indices

        assertThat(indices).has(TestUtil.ValidRingCondition(TestUtil.SIMPLE_DATA))
    }

    @Test
    fun simplificationByAreaShouldNotBreakRing() {

        val indices = PolylineSimplifier.douglasPeucker(TestUtil.MEDIUM_DATA).setWeightLimit(0.001).indices

        assertThat(indices).has(TestUtil.ValidRingCondition(TestUtil.MEDIUM_DATA))
    }

    @Test
    fun tooManyPoints() {

        val indices = PolylineSimplifier.douglasPeucker(TestUtil.COMPLEX_DATA).setCountLimit(13).indices
        assertThat(indices)
                .has(TestUtil.ValidRingCondition(TestUtil.COMPLEX_DATA))
                .containsExactly(0, 10, 25, 34, 44, 50, 58, 69, 82, 95, 106, 113, 122)
    }
}