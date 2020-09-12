package de.debuglevel.simplebenchmark.benchmarks.cpu.prime

import de.debuglevel.simplebenchmark.benchmarks.Benchmark
import de.debuglevel.simplebenchmark.benchmarks.BenchmarkType
import mu.KotlinLogging
import javax.inject.Singleton
import kotlin.system.measureNanoTime

@Singleton
class PrimeBenchmark : Benchmark() {
    private val logger = KotlinLogging.logger {}

    override val name: String = "Prime"
    override val benchmarkType = BenchmarkType.CPU
    override val benchmarkIterations = 10
    override val baselineValue = 3.32141157E10

    override fun getIterationScore(): Double {
        val nanoseconds = measureNanoTime {
            PrimeCalculator.start(400_000)
        }

        return nanoseconds / baselineValue
    }
}