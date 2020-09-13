package de.debuglevel.simplebenchmark.benchmarks.cpu.prime

import de.debuglevel.simplebenchmark.benchmarks.Benchmark
import de.debuglevel.simplebenchmark.benchmarks.BenchmarkType
import mu.KotlinLogging
import javax.inject.Singleton
import kotlin.system.measureNanoTime

@Singleton
class ThreadedPrimeBenchmark(
    private val benchmarkConfiguration: ThreadedPrimeBenchmarkConfiguration
) : Benchmark() {
    private val logger = KotlinLogging.logger {}

    override val name: String = "Threaded Prime"
    override val benchmarkType = BenchmarkType.CPU
    override val enabled = benchmarkConfiguration.enabled
    override val benchmarkIterations = benchmarkConfiguration.iterations
    override val baselineValue = benchmarkConfiguration.baseline
    private val parallelThreads = 128

    override fun getIterationScore(): Double {
        val nanoseconds = measureNanoTime {
            (1..parallelThreads)
                .map {
                    Thread(
                        Runnable {
                            PrimeCalculator.start(50_000)
                        }
                    )
                }
                .onEach { it.start() }
                .onEach { it.join() }
        }

        return nanoseconds / baselineValue
    }
}