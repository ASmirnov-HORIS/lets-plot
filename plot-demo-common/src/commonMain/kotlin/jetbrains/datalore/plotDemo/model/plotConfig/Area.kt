/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.model.plotConfig

import jetbrains.datalore.plot.parsePlotSpec
import jetbrains.datalore.plotDemo.data.Iris
import jetbrains.datalore.plotDemo.model.PlotConfigDemoBase

class Area : PlotConfigDemoBase() {
    fun plotSpecList(): List<Map<String, Any>> {
        return listOf(
            sepalLength()
        )
    }

    private fun sepalLength(): Map<String, Any> {
        val spec = "{" +
                "   'kind': 'plot'," +
                "   'mapping': {" +
                "             'x': 'sepal length (cm)'," +
                "             'group': 'target'," +
                "             'color': 'sepal width (cm)'," +
                "             'fill': 'target'" +
                "           }," +

                "   'layers': [" +
                "               {" +
                "                  'geom': 'area'," +
                "                   'stat': 'density'," +
                "                   'position' : 'identity'," +
                "                   'alpha': 0.7" +
                "               }" +
                "           ]" +
                "}"

        val plotSpec = HashMap(parsePlotSpec(spec))
        plotSpec["data"] = Iris.df
        return plotSpec

    }
}