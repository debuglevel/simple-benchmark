package de.debuglevel.simplebenchmark.benchmarks

import mu.KotlinLogging
import org.nield.kotlinstatistics.median

abstract class Benchmark {
    private val logger = KotlinLogging.logger {}

    abstract val benchmarkIterations: Int
    abstract val baselineValue: Double
    abstract val name: String
    abstract val benchmarkType: BenchmarkType

    abstract fun getIterationScore(): Double

    fun getScore(): Double {
        logger.debug { "Getting median score for $name over $benchmarkIterations iterations..." }

        val medianScore = (1..benchmarkIterations).map {
            logger.trace { "Getting score for $name in iteration $it of $benchmarkIterations..." }
            val score = getIterationScore()
            logger.trace { "Got score for $name in iteration $it of $benchmarkIterations: $score" }
            score
        }.median()

        logger.debug { "Got median score for $name over $benchmarkIterations iterations: $medianScore" }
        return medianScore
    }
}