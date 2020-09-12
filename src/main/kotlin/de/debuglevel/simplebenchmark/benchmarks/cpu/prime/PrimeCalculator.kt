package de.debuglevel.simplebenchmark.benchmarks.cpu.prime


object PrimeCalculator {
    fun start(biggestNumber: Int) {
        primeNumbersBruteForce(biggestNumber)
    }

    private fun primeNumbersBruteForce(n: Int): List<Int> {
        val primeNumbers = mutableListOf<Int>()
        (2..n).mapNotNull {
            if (isPrimeBruteForce(it)) {
                it
            } else {
                null
            }
        }
        return primeNumbers
    }

    private fun isPrimeBruteForce(number: Int): Boolean {
        for (i in 2 until number) {
            if (number % i == 0) {
                return false
            }
        }

        return true
    }
}