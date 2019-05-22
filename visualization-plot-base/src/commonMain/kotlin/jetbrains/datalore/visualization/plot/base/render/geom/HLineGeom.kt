package jetbrains.datalore.visualization.plot.base.render.geom

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.visualization.base.svg.SvgLineElement
import jetbrains.datalore.visualization.plot.base.Aes
import jetbrains.datalore.visualization.plot.base.Aesthetics
import jetbrains.datalore.visualization.plot.base.render.*
import jetbrains.datalore.visualization.plot.base.render.geom.util.GeomHelper
import jetbrains.datalore.visualization.plot.common.data.SeriesUtil

class HLineGeom : GeomBase() {

    override val legendKeyElementFactory: LegendKeyElementFactory
        get() = PathGeom.LEGEND_KEY_ELEMENT_FACTORY

    override fun buildIntern(root: SvgRoot, aesthetics: Aesthetics, pos: PositionAdjustment, coord: CoordinateSystem, ctx: GeomContext) {
        val helper = GeomHelper(pos, coord, ctx)
                .createSvgElementHelper()

        val viewPort = aesViewPort(aesthetics)

        val lines = ArrayList<SvgLineElement>()
        for (p in aesthetics.dataPoints()) {
            val intercept = p.interceptY()
            if (SeriesUtil.isFinite(intercept)) {
                if (viewPort.yRange().contains(intercept!!)) {
                    val start = DoubleVector(viewPort.left, intercept)
                    val end = DoubleVector(viewPort.right, intercept)
                    val line = helper.createLine(start, end, p)
                    lines.add(line)
                }
            }
        }

        lines.forEach { root.add(it) }
    }

    companion object {
        val RENDERS = listOf(
                //Aes.X,
                //Aes.Y,
                Aes.YINTERCEPT,

                Aes.SIZE, // path width
                Aes.LINETYPE,
                Aes.COLOR,
                Aes.ALPHA
        )

        const val HANDLES_GROUPS = false
    }
}