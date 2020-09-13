package de.debuglevel.simplebenchmark.benchmarks.cpu.leibnizpi

import de.debuglevel.simplebenchmark.benchmarks.Benchmark
import de.debuglevel.simplebenchmark.benchmarks.BenchmarkType
import mu.KotlinLogging
import javax.inject.Singleton
import kotlin.system.measureNanoTime

@Singleton
class LeibnizPiBenchmark(
    private val benchmarkConfiguration: LeibnizPiBenchmarkConfiguration
) : Benchmark() {
    private val logger = KotlinLogging.logger {}

    override val name: String = "Leibniz Pi"
    override val benchmarkType = BenchmarkType.CPU
    override val enabled = benchmarkConfiguration.enabled
    override val benchmarkIterations = benchmarkConfiguration.iterations
    override val baselineValue = benchmarkConfiguration.baseline

    override fun getIterationDuration(): Long {
        val nanoseconds = measureNanoTime {
            LeibnizPiCalculator.start(100_000_000)
        }

        return nanoseconds
    }
}