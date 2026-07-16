package com.vtech.euler.project

import com.vtech.euler.project.Problem1.sumOfMultipleOf3And5

object Problem1 {
    fun sumOfMultipleOf3And5(input: Int): Long =
        (intArrayOf(3,5) to 0).let { (dList, zero) ->
            (dList.first()..input).filter { i -> dList.count { i.mod(it) == zero } > zero }
                .map { it.toLong() }.sum()
        }
}

fun main() {
    println(sumOfMultipleOf3And5(999))
}