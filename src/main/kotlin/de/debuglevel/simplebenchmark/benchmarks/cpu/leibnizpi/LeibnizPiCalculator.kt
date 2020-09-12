package de.debuglevel.simplebenchmark.benchmarks.cpu.leibnizpi

import kotlin.math.pow

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
object LeibnizPiCalculator {
    fun start(iterations: Int) {
        calculatePi(iterations)
    }

    private fun calculatePi(iterations: Int): Double {
        var pi = 0.0
        (1..iterations).forEach {
            pi += (-1.0).pow(it + 1.0) / (2 * it - 1)
        }
        return 4 * pi
    }
}