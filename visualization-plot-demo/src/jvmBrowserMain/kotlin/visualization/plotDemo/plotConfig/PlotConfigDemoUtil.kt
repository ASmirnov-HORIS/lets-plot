package jetbrains.datalore.visualization.plotDemo.plotConfig

import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.base.jsObject.mapToJsObjectInitializer
import jetbrains.datalore.visualization.base.browser.BrowserDemoUtil
import jetbrains.datalore.visualization.base.browser.BrowserDemoUtil.BASE_MAPPER_LIBS_JS
import jetbrains.datalore.visualization.base.browser.BrowserDemoUtil.KOTLIN_LIBS_JS
import jetbrains.datalore.visualization.base.browser.BrowserDemoUtil.PLOT_LIBS_JS
import jetbrains.datalore.visualization.plot.server.config.PlotConfigServerSide
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.StringWriter

private const val DEMO_PROJECT = "visualization-plot-demo"
private const val CALL_FUN = "jetbrains.datalore.visualization.plotDemo.plotConfig.buildPlotSvg"
private val LIBS = KOTLIN_LIBS_JS + BASE_MAPPER_LIBS_JS + PLOT_LIBS_JS

internal object PlotConfigDemoUtil {
    fun show(title: String, plotSpecList: List<MutableMap<String, Any>>, plotSize: DoubleVector) {
        BrowserDemoUtil.openInBrowser(DEMO_PROJECT) {
            getHtml(
                title,
                plotSpecList,
                plotSize
            )
        }

    }

    private fun getHtml(
        title: String,
        plotSpecList: List<MutableMap<String, Any>>,
        plotSize: DoubleVector
    ): String {

        val plotSpecListJs = StringBuilder("[\n")
        @Suppress("UNCHECKED_CAST")
        var first = true
        for (spec in plotSpecList) {
            @Suppress("NAME_SHADOWING")
            val spec = PlotConfigServerSide.processTransformWithoutEncoding(spec)
            if (!first) plotSpecListJs.append(',') else first = false
            plotSpecListJs.append(mapToJsObjectInitializer(spec))
        }
        plotSpecListJs.append("\n]")

        val writer = StringWriter().appendHTML().html {
            lang = "en"
            head {
                title(title)
            }
            body {
                div { id = "root" }

                for (lib in LIBS) {
                    script {
                        type = "text/javascript"
                        src = "lib/$lib"
                    }
                }

                script {
                    type = "text/javascript"
                    src = "$DEMO_PROJECT.js"
                }

                script {
                    type = "text/javascript"
                    unsafe {
                        +"""
                        |var plotSpecList=$plotSpecListJs;
                        |plotSpecList.forEach(function (spec, index) {
                        |   var parentElement = document.createElement('div');
                        |   document.getElementById("root").appendChild(parentElement);
                        |   window['$DEMO_PROJECT'].$CALL_FUN(spec, ${plotSize.x}, ${plotSize.y}, parentElement);
                        |});
                    """.trimMargin()

                    }
                }
            }
        }

        return writer.toString()
    }
}