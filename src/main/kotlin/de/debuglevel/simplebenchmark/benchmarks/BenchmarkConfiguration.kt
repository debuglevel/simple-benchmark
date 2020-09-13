package de.debuglevel.simplebenchmark.benchmarks

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

open class BenchmarkConfiguration {
    @NotBlank
    var enabled: Boolean = false

    @NotBlank
    @Min(1L)
    var iterations: Int = 1

    @NotBlank
    @Min(0L)
    var baseline: Double = 1.0
}