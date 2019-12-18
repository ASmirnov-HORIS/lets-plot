/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.livemap.tiles

import jetbrains.datalore.base.geospatial.LonLat
import jetbrains.datalore.base.geospatial.QuadKey
import jetbrains.datalore.base.geospatial.calculateQuadKeys
import jetbrains.datalore.base.geospatial.computeRect
import jetbrains.datalore.base.typedGeometry.Rect
import jetbrains.livemap.projections.MapProjection
import jetbrains.livemap.projections.ProjectionUtil

fun convertCellKeyToQuadKeys(mapProjection: MapProjection, cellKey: CellKey): Set<QuadKey<LonLat>> {
    val cellRect = cellKey.computeRect(mapProjection.mapRect)
    val geoRect = ProjectionUtil.transformBBox(cellRect, mapProjection::invert)
    return calculateQuadKeys(geoRect, cellKey.length)
}

internal fun <T> calculateCellKeys(mapRect: Rect<T>, rect: Rect<T>, zoom: Int): Set<CellKey> {
    return calculateQuadKeys(mapRect, rect, zoom, ::CellKey)
}



