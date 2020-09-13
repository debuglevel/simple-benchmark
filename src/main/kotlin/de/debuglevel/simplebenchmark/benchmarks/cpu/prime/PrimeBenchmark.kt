package de.debuglevel.simplebenchmark.benchmarks.cpu.prime

import de.debuglevel.simplebenchmark.benchmarks.Benchmark
import de.debuglevel.simplebenchmark.benchmarks.BenchmarkType
import mu.KotlinLogging
import javax.inject.Singleton
import kotlin.system.measureNanoTime

@Singleton
class PrimeBenchmark(
    private val benchmarkConfiguration: ThreadedPrimeBenchmarkConfiguration
) : Benchmark() {
    private val logger = KotlinLogging.logger {}

    override val name: String = "Prime"
    override val benchmarkType = BenchmarkType.CPU
    override val enabled = benchmarkConfiguration.enabled
    override val benchmarkIterations = benchmarkConfiguration.iterations
    override val baselineValue = benchmarkConfiguration.baseline

    override fun getIterationScore(): Double {
        val nanoseconds = measureNanoTime {
            PrimeCalculator.start(400_000)
        }

        return nanoseconds / baselineValue
    }
}