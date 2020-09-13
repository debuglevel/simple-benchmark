package de.debuglevel.simplebenchmark.benchmarks

data class GetBenchmarkScoreResponse(
    val name: String,
    val score: Double,
    val duration: Double
) {
    constructor(benchmarkScore: BenchmarkScore) : this(
        benchmarkScore.name,
        benchmarkScore.score,
        benchmarkScore.iterationDuration,
    )
}