package de.debuglevel.simplebenchmark.benchmarks

import mu.KotlinLogging
import org.nield.kotlinstatistics.median
import kotlin.system.measureNanoTime

abstract class Benchmark {
    private val logger = KotlinLogging.logger {}

    abstract val enabled: Boolean
    abstract val benchmarkIterations: Int
    abstract val baselineValue: Double
    abstract val name: String
    abstract val benchmarkType: BenchmarkType

    abstract fun getIterationDuration(): Long

    fun getScore(): BenchmarkScore {
        logger.debug { "Getting median score for $name over $benchmarkIterations iterations..." }

        var medianDuration: Double
        val nanoseconds = measureNanoTime {
            val medianDurationX = (1..benchmarkIterations).map {
                logger.trace { "Getting score for $name in iteration $it of $benchmarkIterations..." }
                val score = getIterationDuration()
                logger.trace { "Got score for $name in iteration $it of $benchmarkIterations: $score" }
                score
            }.median()
            medianDuration = medianDurationX
        }

        val duration = nanoseconds / 1000.0 / 1000.0 / 1000.0
        val score = medianDuration / baselineValue

        logger.debug { "Got median score for $name over $benchmarkIterations iterations: $score" }
        return BenchmarkScore(name, medianDuration, score, duration)
    }
}