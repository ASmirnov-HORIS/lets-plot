package jetbrains.gis.common.twkb

import jetbrains.datalore.base.projectionGeometry.LineString
import jetbrains.datalore.base.projectionGeometry.MultiLineString
import jetbrains.datalore.base.projectionGeometry.MultiPoint
import jetbrains.datalore.base.projectionGeometry.MultiPolygon
import jetbrains.datalore.base.projectionGeometry.Polygon
import jetbrains.datalore.base.geometry.DoubleVector

import jetbrains.gis.common.twkb.VarInt.decodeZigZag

object Twkb {
    internal const val META_ID_LIST_BIT: Int = 2
    internal const val META_EMPTY_GEOMETRY_BIT: Int = 4
    private const val META_BBOX_BIT: Int = 0
    private const val META_SIZE_BIT: Int = 1
    private const val META_EXTRA_PRECISION_BIT: Int = 3

    fun parse(data: ByteArray, geometryConsumer: GeometryConsumer) {
        val parser = Parser(data, geometryConsumer)
        while (parser.next()) {
        }
    }

    fun parser(data: ByteArray, geometryConsumer: GeometryConsumer): Parser {
        return Parser(data, geometryConsumer)
    }

    internal fun isSet(b: Int, bit: Int): Boolean {
        return b and (1 shl bit) != 0
    }

    internal fun isMulti(type: GeometryType): Boolean {
        return (type == GeometryType.MULTI_POINT
                || type == GeometryType.MULTI_LINESTRING
                || type == GeometryType.MULTI_POLYGON
                || type == GeometryType.GEOMETRY_COLLECTION)
    }

    internal fun precision(typeAndPrec: Int): Int {
        return decodeZigZag(typeAndPrec and 0xF0 shr 4)
    }

    internal fun type(typeAndPrec: Int): GeometryType {
        return GeometryType.fromCode(typeAndPrec and 0x0F)
    }

    internal fun assertNoMeta(meta: Int) {
        if (isSet(meta, META_EXTRA_PRECISION_BIT)) {
            throw IllegalStateException("META_EXTRA_PRECISION_BIT is not supported")
        }

        if (isSet(meta, META_SIZE_BIT)) {
            throw IllegalStateException("META_SIZE_BIT is not supported")
        }

        if (isSet(meta, META_BBOX_BIT)) {
            throw IllegalStateException("META_BBOX_BIT is not supported")
        }
    }

    enum class GeometryType private constructor(internal val code: Int) {
        POINT(1),
        LINESTRING(2),
        POLYGON(3),
        MULTI_POINT(4),
        MULTI_LINESTRING(5),
        MULTI_POLYGON(6),
        GEOMETRY_COLLECTION(7);


        companion object {

            internal fun fromCode(code: Int): GeometryType {
                when (code) {
                    1 -> return POINT

                    2 -> return LINESTRING

                    3 -> return POLYGON

                    4 -> return MULTI_POINT

                    5 -> return MULTI_LINESTRING

                    6 -> return MULTI_POLYGON

                    7 -> return GEOMETRY_COLLECTION

                    else -> throw IllegalArgumentException("Unkown geometry type: $code")
                }
            }
        }
    }

    interface GeometryConsumer {
        fun onPoint(p: DoubleVector)
        fun onLineString(lineString: LineString)
        fun onPolygon(polygon: Polygon)
        fun onMultiPoint(multiPoint: MultiPoint, idList: List<Int>)
        fun onMultiLineString(multiLineString: MultiLineString, idList: List<Int>)
        fun onMultipolygon(multipolygon: MultiPolygon, idList: List<Int>)
    }
}
