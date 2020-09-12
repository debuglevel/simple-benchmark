package de.debuglevel.simplebenchmark.benchmarks.cpu

import java.text.DecimalFormat

// see: https://raw.githubusercontent.com/Nefari0uss/calculate-pi/master/Pi.java
/**
 * Calculate Pi
 * @date 19 August 2014
 * @author Nefari0uss
 *
 * This program will request the approximate number of calculations to run in calculating π.
 * The final result will be displayed on the console. Assumption is that the user inputs an int.
 *
 *
 * Uses the Gottfried Leibniz formula for calculation of π:
 *
 * 1 -  1/3  + 1/5 - 1/7 + 1/9 - ... = π/4
 *
 * Source: Wikipedia - Leibniz formula for π
 */
object Pi {
    fun start(iterations: Int) {
        val n = iterations
        val piValue = calculatePi(n.toDouble())
        printResult(piValue)
    }

    private fun calculatePi(n: Double): Double {
        var pi = 0.0
        var i = 1
        while (i < n) {
            pi += Math.pow(-1.0, i + 1.toDouble()) / (2 * i - 1)
            i++
        }
        return 4 * pi
    }

    private fun calculateError(piValue: Double): Double {
        return Math.abs(1 - piValue / Math.PI) * 100
    }

    private fun printResult(piValue: Double) {
        val df = DecimalFormat("#.##")
        println("The value of pi is approximately $piValue.")
        println("The calculated value is off by approximately " + df.format(calculateError(piValue)) + "%.")
    }
}