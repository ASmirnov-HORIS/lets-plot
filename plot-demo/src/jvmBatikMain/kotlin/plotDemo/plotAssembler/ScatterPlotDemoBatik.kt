/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.plotAssembler

import jetbrains.datalore.plotDemo.model.plotAssembler.ScatterPlotDemo
import jetbrains.datalore.vis.demoUtils.PlotObjectsViewerDemoWindowBatik

fun main(args: Array<String>) {
    with(ScatterPlotDemo()) {
        PlotObjectsViewerDemoWindowBatik(
            "Scatter plot",
            plotList = createPlots()
        ).open()
    }
}
