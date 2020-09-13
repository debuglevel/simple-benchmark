package de.debuglevel.simplebenchmark.benchmarks

data class GetBenchmarkResponse(
    val name: String,
    val enabled: Boolean,
    val baseline: Double,
    val iterations: Long,
    val type: BenchmarkType
) {
    constructor(benchmark: Benchmark) : this(
        name = benchmark.name,
        enabled = benchmark.enabled,
        baseline = benchmark.baselineValue,
        iterations = benchmark.benchmarkIterations.toLong(),
        type = benchmark.benchmarkType
    )
}