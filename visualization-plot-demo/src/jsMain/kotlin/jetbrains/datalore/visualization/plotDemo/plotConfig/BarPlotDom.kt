package jetbrains.datalore.visualization.plotDemo.plotConfig

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.observable.property.ValueProperty
import jetbrains.datalore.visualization.base.svg.SvgNodeContainer
import jetbrains.datalore.visualization.base.svgMapper.dom.SvgRootDocumentMapper
import jetbrains.datalore.visualization.plot.MonolithicJs
import jetbrains.datalore.visualization.plot.builder.PlotContainer
import jetbrains.datalore.visualization.plotDemo.model.KansasPolygon
import org.w3c.dom.svg.SVGSVGElement


/**
 *  This is to become `visualization-plot-demo.js`
 *  Run the demo using `BarPlotBrowser.main()`
 */
@kotlinx.serialization.ImplicitReflectionSerializer
fun main() {
//    with(BarPlot()) {
//        @Suppress("UNCHECKED_CAST")
//        val plotSpecList = plotSpecList() as List<MutableMap<String, Any>>
//
//        val svg = mapPlotToSvg(plotSpecList[0], demoComponentSize)
//        document.getElementById("root")!!.appendChild(svg)
//    }
    println("BarPlot main running")
    bbb12345()
    val kansasY = KansasPolygon.KANSAS_Y
}

private fun mapPlotToSvg(plotSpec: MutableMap<String, Any>, plotSize: DoubleVector): SVGSVGElement {
    val plot = MonolithicJs.createPlot(plotSpec, null)
    val plotContainer = PlotContainer(plot, ValueProperty(plotSize))
    plotContainer.ensureContentBuilt()

    val svgRoot = plotContainer.svg
    val mapper = SvgRootDocumentMapper(svgRoot)
    SvgNodeContainer(svgRoot)
    mapper.attachRoot()
    return mapper.target
}

fun aaa12345(map: Map<String, Any>) {
    for ((k, v) in map) {
        println("Key: $k")
        println("Val: $v")
    }
}

fun bbb12345() {
    println("Hello bbb12345!!!")
}

