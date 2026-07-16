package com.vtech.euler.project

import com.vtech.euler.project.Problem16.powerDigitSum
import com.vtech.fold
import kotlin.math.pow

object Problem16 {
    fun powerDigitSum(baseNum: Int, powerNum: Int): Long =
        baseNum.toDouble().pow(powerNum.toDouble()).toLong().apply {
            println("$baseNum ^ $powerNum = $this")
        }.fold(0L){ acc, q, r ->
            acc + r
        }
}

fun main() {
    println((2 to 15).let { "${it.first} ^ ${it.second} digit sum is: ${powerDigitSum(it.first, it.second)}" })
    println((2 to 100).let { "${it.first} ^ ${it.second} digit sum is: ${powerDigitSum(it.first, it.second)}" })
}