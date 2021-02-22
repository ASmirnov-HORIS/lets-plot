/*
 * Copyright (c) 2020. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.livemap.canvasDemo

import javafx.application.Application
import javafx.stage.Stage

class PathDemoJfx : Application() {

    override fun start(theStage: Stage) {
        BaseCanvasDemoJfx { canvas, _ ->
            PathDemoModel(canvas)
        }.start(theStage)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(PathDemoJfx::class.java, *args)
        }
    }
}