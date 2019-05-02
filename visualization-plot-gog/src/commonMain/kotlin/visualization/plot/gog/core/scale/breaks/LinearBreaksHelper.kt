package jetbrains.datalore.visualization.plot.gog.core.scale.breaks

import jetbrains.datalore.base.function.Function
import jetbrains.datalore.base.gcommon.collect.ClosedRange
import kotlin.math.*

class LinearBreaksHelper(rangeStart: Double, rangeEnd: Double, count: Int) : BreaksHelperBase(rangeStart, rangeEnd, count) {
    val breaks: List<Double>
    val labelFormatter: Function<in Any, String>

    init {

        // compute step so that it is multiple of 10, 5 or 2.
        var step = targetStep
        val start = normalStart
        val end = normalEnd
        val ticks: MutableList<Double>
        if (step > 0) {
            val step10Power = floor(log10(step))
            step = 10.0.pow(step10Power)
            val error = step * count / span
            if (error <= 0.15) {
                step *= 10.0
            } else if (error <= 0.35) {
                step *= 5.0
            } else if (error <= 0.75) {
                step *= 2.0
            }

            // extend range to allow for FP errors
            val delta = step / 10000
            val startE = start - delta
            val endE = end + delta

            ticks = ArrayList()
            var tick = ceil(startE / step) * step
            if (start >= 0 && startE < 0) {
                // avoid negative zero
                tick = 0.0
            }
            while (tick <= endE) {
                // don't allow ticks to go beyond the range
                tick = min(tick, end)

                ticks.add(tick)
                tick += step
            }
        } else {
            ticks = mutableListOf(start)
        }

        // auto format
        val range = ClosedRange.closed(start, end)
        labelFormatter = QuantitativeTickFormatterFactory.forLinearScale().getFormatter(range, step)

        if (isReversed) {
            ticks.reverse()
        }
        breaks = ticks
    }
}
