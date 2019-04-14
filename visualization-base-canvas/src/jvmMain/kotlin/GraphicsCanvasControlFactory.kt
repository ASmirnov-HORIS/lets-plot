package jetbrains.datalore.visualization.base.canvas

import jetbrains.datalore.base.geometry.Vector

interface GraphicsCanvasControlFactory {
    fun create(size: Vector, repaint: Runnable): GraphicsCanvasControl
}
