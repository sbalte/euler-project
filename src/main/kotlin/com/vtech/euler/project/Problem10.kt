package com.vtech.euler.project

import com.vtech.euler.project.Problem10.findPrimeSum
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.sqrt

object Problem10 {
    private fun isPrime(sNum: ULong): Boolean =
        when {
            sNum == 2UL -> true
            sNum < 2UL || sNum.mod(2UL) == 0UL -> false
            else -> (3UL..sqrt(sNum.toDouble()).toULong() step 2).firstOrNull { sNum.mod(it) == 0UL }
                ?.let { false } ?: true
        }
    fun findPrimeSum(numPrimeSum: ULong): ULong = sequence {
        yield(2UL)
        (3UL..numPrimeSum step 2).map { yield(it) }
    }.filter { isPrime(it) }.sum()
}

fun main() = listOf(10UL, 2_000_000UL).forEach {
    println("ULong max: ${ULong.MAX_VALUE} and min: ${ULong.MIN_VALUE}")
    println("Sum of first $it prime: ${findPrimeSum(it)}")
}