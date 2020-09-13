package de.debuglevel.simplebenchmark.benchmarks

data class BenchmarkScore(
    val name: String,
    val iterationDuration: Double,
    val score: Double,
    val benchmarkDuration: Double,
)