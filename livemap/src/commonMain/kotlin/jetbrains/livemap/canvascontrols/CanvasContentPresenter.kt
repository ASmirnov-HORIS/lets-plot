package jetbrains.livemap.canvascontrols

import jetbrains.datalore.visualization.base.canvas.CanvasControl

internal class CanvasContentPresenter {
    lateinit var canvasControl: CanvasControl
    private var canvasContent: CanvasContent = EMPTY_CANVAS_CONTENT


    fun show(content: CanvasContent) {
        canvasContent.hide()
        canvasContent = content
        canvasContent.show(canvasControl)
    }

    fun clear() {
        show(EMPTY_CANVAS_CONTENT)
    }

    private class EmptyContent : CanvasContent {
        override fun show(parentControl: CanvasControl) {}

        override fun hide() {}
    }

    companion object {
        private val EMPTY_CANVAS_CONTENT = EmptyContent()
    }
}