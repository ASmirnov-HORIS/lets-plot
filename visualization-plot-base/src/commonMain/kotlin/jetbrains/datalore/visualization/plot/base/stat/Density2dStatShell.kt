package jetbrains.datalore.visualization.plot.base.stat

import jetbrains.datalore.visualization.plot.base.Aes
import jetbrains.datalore.visualization.plot.base.StatContext
import jetbrains.datalore.visualization.plot.base.data.DataFrame

open class Density2dStatShell : BaseStat(DEF_MAPPING) {

    var adjust = DEF_ADJUST
    var nx = DEF_N
        set(n) {
            if (n > MAX_N) {
                throw IllegalArgumentException("The input Nx " + n + " > " + MAX_N + "is too large!")
            }
            field = n
        }
    var ny = DEF_N
        set(n) {
            if (n > MAX_N) {
                throw IllegalArgumentException("The input Ny " + n + " > " + MAX_N + "is too large!")
            }
            field = n
        }
    var isContour = DEF_CONTOUR
    var bandWidthMethod: DensityStat.BandWidthMethod = DensityStat.BandWidthMethod.NRD
        set(bw) {
            field = bw
            bandWidths = null
        }
    private var myBinCount = DEF_BIN_COUNT
    private var myBinWidth: Double = 0.toDouble()

    protected var bandWidths: DoubleArray? = null
        private set
    protected var kernel: ((Double) -> Double)? = null
        private set

    protected val binOptions: StatUtil.BinOptions
        get() = StatUtil.BinOptions(myBinCount, myBinWidth)

    init {
        setKernel(DensityStat.Kernel.GAUSSIAN)
    }

    fun setBinCount(bin: Int) {
        myBinCount = bin
    }

    fun setBinWidth(bin: Double) {
        myBinWidth = bin
    }

    fun setBandWidthX(bw: Double) {
        //myBW = BandWidth.DOUBLE;
        bandWidths = DoubleArray(2)
        bandWidths?.set(0, bw)
    }

    fun setBandWidthY(bw: Double) {
        //myBW = BandWidth.DOUBLE;
        bandWidths?.set(1, bw)
    }

    fun setKernel(kernel: DensityStat.Kernel) {
        this.kernel = DensityStatUtil.kernel(kernel)
    }

    override fun apply(data: DataFrame, statCtx: StatContext): DataFrame {
        throw IllegalStateException("'density2d' statistic can't be executed on the client side")
    }

    override fun requires(): List<Aes<*>> {
        return listOf(Aes.X, Aes.Y)
    }

    companion object {
        const val DEF_KERNEL = "gaussian"
        const val DEF_ADJUST = 1.0
        const val DEF_N = 100
        const val DEF_BW = "nrd"
        const val DEF_CONTOUR = true
        const val DEF_BIN_COUNT = 10
        private val DEF_MAPPING: Map<Aes<*>, DataFrame.Variable> = mapOf(
                Aes.X to Stats.X,
                Aes.Y to Stats.Y
        )
        private const val MAX_N = 999
    }
}
