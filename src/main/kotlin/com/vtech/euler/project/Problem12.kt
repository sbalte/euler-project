package com.vtech.euler.project

import com.vtech.euler.project.Problem12.highlyDivisibleTriangularNumber
import com.vtech.foldWhile
import kotlin.collections.mutableMapOf
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.incrementAndFetch

object Problem12 {
    fun highlyDivisibleTriangularNumber(numDivisor: Int): Map<UInt, List<UInt>> =
        (1U..UInt.MAX_VALUE).asSequence().map { num ->
            (num * num.plus(1U)).div(2U).let { sum ->
                sum to (1U..sum.div(2U).inc()).asSequence().filter { sum % it == 0U }.toMutableList().also { if(sum != 1U)it += sum }
            }
        }.foldWhile(
            0U to mutableMapOf<UInt, List<UInt>>(),
            { acc, _ ->
                acc.second.getOrDefault(acc.first, emptyList()).size < numDivisor
            }) { acc, item ->
            item.first to acc.second.let { it[item.first] = item.second; acc.second }
        }.second.filter { it.key != 0U }
}

@OptIn(ExperimentalAtomicApi::class)
fun main(): Unit = (highlyDivisibleTriangularNumber(500) to AtomicInt(0)).let { (map, counter) ->
    map.keys.stream().forEachOrdered { sum ->
        println(
            ">>Sum of first ${counter.incrementAndFetch()} natural number is: $sum and its divisors: ${map[sum]} and size: ${
                map.getOrDefault(
                    sum,
                    emptyList()
                ).size
            }"
        )
    }
}
