package jetbrains.datalore.visualization.plot.gog.plot.assemble.geom

import jetbrains.datalore.base.function.Predicate
import jetbrains.datalore.base.gcommon.collect.Iterables
import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.POINT_X
import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.POINT_Y
import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.RECT_XMAX
import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.RECT_XMIN
import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.RECT_YMAX
import jetbrains.datalore.visualization.plot.gog.config.GeoPositionsDataUtil.RECT_YMIN
import jetbrains.datalore.visualization.plot.gog.core.data.DataFrame
import jetbrains.datalore.visualization.plot.gog.core.data.DataFrameUtil
import jetbrains.datalore.visualization.plot.gog.core.render.Aes
import jetbrains.datalore.visualization.plot.gog.plot.assemble.AesAutoMapper

internal class DefaultAesAutoMapper(private val myAutoMappedAes: List<Aes<*>>, private val myPreferDiscreteValues: Predicate<Aes<*>>) : AesAutoMapper {

    override fun createMapping(data: DataFrame): Map<Aes<*>, DataFrame.Variable> {
        val autoMapping = HashMap<Aes<*>, DataFrame.Variable>()
        val doneVars = HashSet<DataFrame.Variable>()

        var variables: Iterable<DataFrame.Variable> = data.variables()
        variables = DataFrameUtil.sortedCopy(variables)

        for (aes in myAutoMappedAes) {
            val discrete = myPreferDiscreteValues(aes)

            val predicate = { variable: DataFrame.Variable? ->
                if (doneVars.contains(variable)) {
                    false
                } else {
                    discrete && !data.isNumeric(variable!!) || !discrete && data.isNumeric(variable!!)
                }
            }

            var autoMapVar: DataFrame.Variable? = null

            if (AES_DEFAULT_LABELS.containsKey(aes)) {
                val defaultLabels = AES_DEFAULT_LABELS.get(aes)!!
                autoMapVar = Iterables.find(variables, { variable -> defaultLabels.contains(variable!!.name) }, null)
            }

            if (autoMapVar == null || !predicate(autoMapVar)) {
                autoMapVar = Iterables.find(variables, predicate, null)
            }

            if (autoMapVar != null) {
                autoMapping[aes] = autoMapVar
                doneVars.add(autoMapVar)
            }
        }
        return autoMapping
    }

    companion object {
        private val AES_DEFAULT_LABELS = mapOf(
                Aes.X to listOf(POINT_X),
                Aes.Y to listOf(POINT_Y),
                Aes.XMIN to listOf(RECT_XMIN),
                Aes.YMIN to listOf(RECT_YMIN),
                Aes.XMAX to listOf(RECT_XMAX),
                Aes.YMAX to listOf(RECT_YMAX)
        )
    }
}