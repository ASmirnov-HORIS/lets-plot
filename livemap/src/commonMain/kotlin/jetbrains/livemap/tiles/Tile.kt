package jetbrains.livemap.tiles

import jetbrains.datalore.visualization.base.canvas.Canvas
import jetbrains.livemap.core.LinkedList

interface Tile {
    class SnapshotTile(val snapshot: Canvas.Snapshot) : Tile

    class SubTile(val tile: Tile, val subKey: String) : Tile

    class CompositeTile : Tile {
        val tiles = LinkedList<Pair<Tile, String>>()

        fun add(tile: Tile, subKey: String) {
            tiles.append(Pair(tile, subKey))
        }
    }

    class EmptyTile : Tile {
        companion object {
            val EMPTY_TILE = EmptyTile()
        }
    }
}

