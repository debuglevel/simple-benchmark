package de.debuglevel.simplebenchmark.benchmarks.cpu.prime

import de.debuglevel.simplebenchmark.benchmarks.BenchmarkConfiguration
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("app.simplebenchmark.benchmarks.threadedprime")
class ThreadedPrimeBenchmarkConfiguration : BenchmarkConfiguration()