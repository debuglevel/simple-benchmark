package de.debuglevel.simplebenchmark.benchmarks.cpu.euler

import de.debuglevel.simplebenchmark.benchmarks.Benchmark
import de.debuglevel.simplebenchmark.benchmarks.BenchmarkType
import mu.KotlinLogging
import javax.inject.Singleton
import kotlin.system.measureNanoTime

@Singleton
class EulerBenchmark(
    private val benchmarkConfiguration: EulerBenchmarkConfiguration
) : Benchmark() {
    private val logger = KotlinLogging.logger {}

    override val name: String = "Euler"
    override val benchmarkType = BenchmarkType.CPU
    override val enabled = benchmarkConfiguration.enabled
    override val benchmarkIterations = benchmarkConfiguration.iterations
    override val baselineValue = benchmarkConfiguration.baseline

    override fun getIterationDuration(): Long {
        val nanoseconds = measureNanoTime {
            EulerCalculator.start(Int.MAX_VALUE)
        }

        return nanoseconds
    }
}