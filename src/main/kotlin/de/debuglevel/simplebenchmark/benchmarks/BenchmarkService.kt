package de.debuglevel.simplebenchmark.benchmarks

import de.debuglevel.simplebenchmark.Application
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class BenchmarkService {
    private val logger = KotlinLogging.logger {}

    fun runAll(): List<BenchmarkScore> {
        logger.debug { "Running all benchmarks..." }

        val benchmarks = Application.applicationContext.getBeansOfType(Benchmark::class.java)
            .filter { it.enabled }

        val scores = benchmarks
            .map {
                logger.debug { "Running benchmark ${it.name}..." }
                it.getScore()
            }

        logger.debug { "Ran ${benchmarks.size} benchmarks" }
        return scores
    }

    fun listAll(): List<Benchmark> {
        logger.debug { "Getting all benchmarks..." }

        val benchmarks = Application.applicationContext.getBeansOfType(Benchmark::class.java)

        logger.debug { "Got ${benchmarks.size} benchmarks" }
        return benchmarks.toList()
    }
}