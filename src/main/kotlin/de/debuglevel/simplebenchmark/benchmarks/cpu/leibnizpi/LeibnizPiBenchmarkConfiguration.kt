package de.debuglevel.simplebenchmark.benchmarks.cpu.leibnizpi

import de.debuglevel.simplebenchmark.benchmarks.BenchmarkConfiguration
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("app.simplebenchmark.benchmarks.leibnizpi")
class LeibnizPiBenchmarkConfiguration : BenchmarkConfiguration()