package com.vtech.euler.project

import com.vtech.euler.project.Problem3.PConst
import com.vtech.euler.project.Problem3.primeFactors
import com.vtech.foldWhile
import kotlin.math.sqrt

object Problem3 {
    const val PConst: ULong = 600851475143UL
//    const val PConst: ULong = 13195UL
    private fun isPrime(sNum: ULong): Boolean =
        when {
            sNum == 2UL -> true
            sNum < 2UL || sNum.mod(2UL) == 0UL -> false
            else -> (3UL..sqrt(sNum.toDouble()).toULong() step 2).firstOrNull { sNum.mod(it) == 0UL }
                ?.let { false } ?: true
        }
    fun primeList(num: ULong): Sequence<ULong> =
        (1UL .. num).asSequence()
            .filter { it == 2UL || it.mod(2UL) != 0UL }
            .filter { isPrime(it) }

    fun largestPrimeFactor(num: ULong): ULong =
        primeList(num).let { pList -> pList.last{ PConst.mod(it) == 0UL } }

    fun primeFactors(num: ULong): List<ULong> =
        primeList(num).foldWhile(mutableListOf<ULong>() to num,
            { acc, item ->
                acc.second > 1UL
            }) { acc, item ->
            var tAcc = acc
            while(tAcc.second.mod(item) == 0UL) {
                tAcc.first.add(item)
                tAcc = tAcc.first to (tAcc.second/item)
            }
            tAcc
        }.first
}

fun main() {
//    println(largestPrimeFactor(PConst))
    listOf<ULong>(13195UL, PConst).forEach {
        println("$it prime factors are: ${primeFactors(it)}")
    }
}