package de.debuglevel.simplebenchmark.benchmarks

data class GetBenchmarkResponse(
    val name: String,
    val score: Double
) {
    constructor(benchmarkScore: BenchmarkScore) : this(
        benchmarkScore.name,
        benchmarkScore.score,
    )
}