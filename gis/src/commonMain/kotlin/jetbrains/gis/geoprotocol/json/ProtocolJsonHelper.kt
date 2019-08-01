package jetbrains.gis.geoprotocol.json

import jetbrains.datalore.base.projectionGeometry.GeoRectangle
import jetbrains.gis.common.json.FluentObject
import jetbrains.gis.common.json.Obj
import jetbrains.gis.common.json.getDouble

internal object ProtocolJsonHelper {
    private const val MIN_LON = "min_lon"
    private const val MIN_LAT = "min_lat"
    private const val MAX_LON = "max_lon"
    private const val MAX_LAT = "max_lat"

    fun parseGeoRectangle(obj: Obj): GeoRectangle {
        return GeoRectangle(
            obj.getDouble(MIN_LON),
            obj.getDouble(MIN_LAT),
            obj.getDouble(MAX_LON),
            obj.getDouble(MAX_LAT)
        )
    }

    fun formatGeoRectangle(rect: GeoRectangle): FluentObject =
        FluentObject()
            .put(MIN_LON, rect.minLongitude())
            .put(MIN_LAT, rect.minLatitude())
            .put(MAX_LAT, rect.maxLatitude())
            .put(MAX_LON, rect.maxLongitude())
}
