package com.vtech.euler.project

import com.vtech.euler.project.Problem20.factorialDigitSum
import com.vtech.foldBI
import java.math.BigInteger

object Problem20 {
    fun factorialDigitSum(num: Int): BigInteger =
        (1UL..num.toULong()).map { it.toBigInteger() }.reduce { acc, i -> acc * i }
            .also { println("Factorial for $num is $it") }
            .foldBI(0.toBigInteger()) { acc, quotient, reminder -> acc + reminder }
}

fun main() {
    println("Sum of the digits of the factorial of 100: ${factorialDigitSum(100)}")
}