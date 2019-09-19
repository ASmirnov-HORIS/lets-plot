package jetbrains.datalore.jetbrains.livemap.entities.regions

import jetbrains.datalore.base.gcommon.base.Preconditions.checkArgument
import jetbrains.datalore.base.projectionGeometry.QuadKey
import jetbrains.gis.geoprotocol.Geometry
import jetbrains.livemap.core.ecs.EcsComponentManager
import jetbrains.livemap.core.ecs.EcsEntity
import jetbrains.livemap.entities.regions.FragmentKey
import jetbrains.livemap.entities.regions.Utils.entityName

class FragmentSpec (private var myKey: FragmentKey) {
    private var myGeometries: Geometry? = null
    private var myEntity: EcsEntity? = null

    internal constructor(regionId: String, quad: QuadKey) : this(FragmentKey(regionId, quad))

    fun quad(): QuadKey {
        return myKey.quadKey
    }

    fun regionId(): String {
        return myKey.regionId
    }

    fun key(): FragmentKey {
        return myKey
    }

    fun name(): String {
        return entityName(key())
    }


    fun geometries(): Geometry? {
        return myGeometries
    }

    internal fun setGeometries(geometries: Geometry): FragmentSpec {
        myGeometries = geometries
        return this
    }

    fun withReadyEntity(componentManager: EcsComponentManager): FragmentSpec {
        myEntity = componentManager.createEntity(name())
        return this
    }

    fun readyEntity(): EcsEntity? {
        checkArgument(myGeometries != null && !myGeometries!!.asMultipolygon().isEmpty())
        return myEntity
    }

    companion object {
        fun quads(vararg specs: FragmentSpec): Array<QuadKey> {
            return listOf(*specs).map { it.quad() }.toTypedArray()
        }
    }
}