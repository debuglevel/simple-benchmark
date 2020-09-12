package de.debuglevel.simplebenchmark.benchmarks.cpu.euler

// see: https://stackoverflow.com/a/39067189/4764279
object EulerCalculator {
    fun start(iterations: Int) {
        calculateEuler(iterations)
    }

    private fun calculateEuler(iterations: Int): Double {
        var e = 1.0
        var f = 1.0
        for (i in 1..iterations) {
            f *= (1.0 / i)
            if (f == 0.0) break
            e += f
        }
        return e
    }
}