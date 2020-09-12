package de.debuglevel.simplebenchmark.benchmarks.cpu.leibnizpi

import de.debuglevel.simplebenchmark.benchmarks.Benchmark
import de.debuglevel.simplebenchmark.benchmarks.BenchmarkType
import mu.KotlinLogging
import javax.inject.Singleton
import kotlin.system.measureNanoTime

@Singleton
class LeibnizPiBenchmark : Benchmark() {
    private val logger = KotlinLogging.logger {}

    override val name: String = "Leibniz Pi"
    override val benchmarkType = BenchmarkType.CPU
    override val benchmarkIterations = 10
    override val baselineValue = 10061219900.0

    override fun getIterationScore(): Double {
        val nanoseconds = measureNanoTime {
            LeibnizPiCalculator.start(100_000_000)
        }

        return nanoseconds / baselineValue
    }
}