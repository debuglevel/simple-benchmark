package de.debuglevel.simplebenchmark.benchmarks

data class GetBenchmarkScoreResponse(
    val name: String,
    val score: Double,
    val iterationDuration: Double,
    val benchmarkDuration: Double,
) {
    constructor(benchmarkScore: BenchmarkScore) : this(
        benchmarkScore.name,
        benchmarkScore.score,
        benchmarkScore.iterationDuration,
        benchmarkScore.benchmarkDuration,
    )
}