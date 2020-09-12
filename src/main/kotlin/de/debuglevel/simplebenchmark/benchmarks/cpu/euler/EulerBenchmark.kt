package de.debuglevel.simplebenchmark.benchmarks.cpu.euler

import de.debuglevel.simplebenchmark.benchmarks.Benchmark
import de.debuglevel.simplebenchmark.benchmarks.BenchmarkType
import mu.KotlinLogging
import javax.inject.Singleton
import kotlin.system.measureNanoTime

@Singleton
class EulerBenchmark : Benchmark() {
    private val logger = KotlinLogging.logger {}

    override val name: String = "Euler"
    override val benchmarkType = BenchmarkType.CPU
    override val benchmarkIterations = 40_000_000
    override val baselineValue = 1799.0

    override fun getIterationScore(): Double {
        val nanoseconds = measureNanoTime {
            EulerCalculator.start(Int.MAX_VALUE)
        }

        return nanoseconds / baselineValue
    }
}