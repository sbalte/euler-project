package com.vtech.euler.project

import com.vtech.euler.project.Problem2.fibEvenSum

object Problem2 {
    private fun fibList(num: UInt): List<UInt> =
        assert(num >= 0U) { "input number $num need to be >= 0" }.let {
                (1U until num).fold((0U to 1U) to mutableListOf(0U)) { acc, _ ->
                    acc.second.add(acc.first.second)
                    (acc.first.second to (acc.first.first + acc.first.second)) to acc.second
                }
        }.second

    fun fibEvenSum(input: UInt): ULong =
        fibList(input)
            .also { println("Fibonacci List: $it") }
            .sumOf { value -> value.toULong().let { if(it.mod(2U) == 0U) it else 0U } }
}

fun main() {
    println(fibEvenSum(100U))
}