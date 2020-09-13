package de.debuglevel.simplebenchmark.benchmarks.cpu.euler

import de.debuglevel.simplebenchmark.benchmarks.BenchmarkConfiguration
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("app.simplebenchmark.benchmarks.euler")
class EulerBenchmarkConfiguration : BenchmarkConfiguration()