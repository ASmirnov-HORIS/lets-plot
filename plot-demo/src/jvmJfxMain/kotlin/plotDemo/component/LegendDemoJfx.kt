/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.plotDemo.component

import jetbrains.datalore.plotDemo.model.component.LegendDemo
import jetbrains.datalore.vis.demoUtils.SvgViewerDemoWindowJfx

fun main() {
    with(LegendDemo()) {
        SvgViewerDemoWindowJfx(
            "Legend component (JFX SVG mapper)",
            createSvgRoots(createModels())
        ).open()
    }
}