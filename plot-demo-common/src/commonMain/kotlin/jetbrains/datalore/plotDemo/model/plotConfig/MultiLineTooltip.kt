/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.model.plotConfig

import jetbrains.datalore.plot.parsePlotSpec
import jetbrains.datalore.plotDemo.data.Iris
import jetbrains.datalore.plotDemo.model.PlotConfigDemoBase

open class MultiLineTooltip : PlotConfigDemoBase() {
    fun plotSpecList(): List<Map<String, Any>> {
        return listOf(
            oneLine(),
            oneLineDigit(),
            twoLines(),
            threeLines()
        )
    }

    companion object {
        private const val OUR_DATA = "{'name': ['Aa','HH', 'Jj', 'hj', 'jp', 'aq', 'aa']}"

        fun oneLine(): Map<String, Any> {
            val spec = "{" +
                    "   'kind': 'plot'," +
                    "   'data': " + OUR_DATA +
                    "           ," +
                    "   'mapping': {" +
                    "             'x' : 'name', 'color' : 'name'" +
                    "           }," +
                    "   'layers': [" +
                    "               {" +
                    "                  'geom': 'point'" +
                    "               }" +
                    "           ]" +
                    "}"

            return parsePlotSpec(spec)
        }

        fun oneLineDigit(): Map<String, Any> {
            val spec = "{" +
                    "   'kind': 'plot'," +
                    "   'data': " + OUR_DATA +
                    "           ," +
                    "   'mapping': {" +
                    "             'x': 'name'" +
                    "           }," +
                    "   'layers': [" +
                    "               {" +
                    "                  'geom': 'bar'" +
                    "               }" +
                    "           ]" +
                    "}"

            return parsePlotSpec(spec)
        }

        fun twoLines(): Map<String, Any> {
            val spec = "{" +
                    "   'kind': 'plot'," +
                    "   'data': " + OUR_DATA +
                    "           ," +
                    "   'mapping': {" +
                    "             'x': 'name'," +
                    "              'fill' : 'name'" +
                    "           }," +
                    "   'layers': [" +
                    "               {" +
                    "                  'geom': 'bar'" +
                    "               }" +
                    "           ]" +
                    "}"

            return parsePlotSpec(spec)
        }

        fun threeLines(): Map<String, Any> {
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
                    "                   'geom': 'area'," +
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
}