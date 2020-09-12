package de.debuglevel.simplebenchmark.benchmarks.cpu

import org.nield.kotlinstatistics.median
import kotlin.system.measureNanoTime

class PiBenchmark : CpuBenchmark {

    private val benchmarkIterations = 10

    fun getScore(): Double {
        val medianScore = (1..benchmarkIterations).map {
            getIterationScore()
        }.median()

        return medianScore
    }

    private fun getIterationScore(): Double {
        val relativator = 10061219900.0

        val nanoseconds = measureNanoTime {
            Pi.start(100_000_000)
        }

        val score = nanoseconds / relativator

        println(score)
        return score
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val score = PiBenchmark().getScore()
            println("Median:" + score)
        }
    }
}