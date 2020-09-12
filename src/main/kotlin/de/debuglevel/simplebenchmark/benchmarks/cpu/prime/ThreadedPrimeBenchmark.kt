package de.debuglevel.simplebenchmark.benchmarks.cpu.prime

import de.debuglevel.simplebenchmark.benchmarks.Benchmark
import de.debuglevel.simplebenchmark.benchmarks.BenchmarkType
import mu.KotlinLogging
import javax.inject.Singleton
import kotlin.system.measureNanoTime

@Singleton
class ThreadedPrimeBenchmark : Benchmark() {
    private val logger = KotlinLogging.logger {}

    override val name: String = "Threaded Prime"
    override val benchmarkType = BenchmarkType.CPU
    override val benchmarkIterations = 10
    override val baselineValue = 5.29087474E10

    override fun getIterationScore(): Double {
        val nanoseconds = measureNanoTime {
            (1..128)
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