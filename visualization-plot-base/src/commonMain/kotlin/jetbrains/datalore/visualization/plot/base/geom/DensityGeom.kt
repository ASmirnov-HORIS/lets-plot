package jetbrains.datalore.visualization.plot.base.geom

import jetbrains.datalore.visualization.plot.base.DataPointAesthetics
import jetbrains.datalore.visualization.plot.base.geom.util.HintColorUtil.fromColor
import jetbrains.datalore.visualization.plot.base.interact.GeomTargetCollector.TooltipParams
import jetbrains.datalore.visualization.plot.base.interact.GeomTargetCollector.TooltipParams.Companion.params

class DensityGeom : AreaGeom() {

    override fun setupTooltipParams(aes: DataPointAesthetics): TooltipParams {
        return params().setColor(fromColor(aes))
    }

    companion object {
//        val RENDERS: List<Aes<*>> = AreaGeom.RENDERS

        const val HANDLES_GROUPS = AreaGeom.HANDLES_GROUPS
    }


}
