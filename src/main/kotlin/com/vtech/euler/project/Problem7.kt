package com.vtech.euler.project

import com.vtech.euler.project.Problem7.getNthPrime
import com.vtech.foldWhile
import kotlin.math.sqrt

object Problem7 {
    private fun isPrime(sNum: ULong): Boolean =
        when {
            sNum == 2UL -> true
            sNum < 2UL || sNum.mod(2UL) == 0UL -> false
            else -> (3UL..sqrt(sNum.toDouble()).toULong() step 2).firstOrNull { sNum.mod(it) == 0UL }
                ?.let { false } ?: true
        }
    fun findNextPrime(num: ULong): ULong =
        (num.inc().inc()..ULong.MAX_VALUE step 2L).asSequence()
            .firstOrNull { isPrime(it) } ?: throw RuntimeException("No prime found greater than $num")

    fun getNthPrime(nthNum: ULong): ULong =
        (1UL..nthNum).asSequence()
            .foldWhile(3UL to mutableListOf(2UL, 3UL),
                { acc, _ -> acc.second.size.toULong() < nthNum }) { acc, _ ->
                findNextPrime(acc.first).let { acc.second.add(it); it to acc.second }
            }.let { it.second[nthNum.dec().toInt()] }
}

fun main() {
    listOf(1UL, 2UL, 6UL, 10UL, 10_001UL, 1_000_000UL).forEach { println("${it}th prime is ${getNthPrime(it)}") }
}