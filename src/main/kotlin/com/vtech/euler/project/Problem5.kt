package com.vtech.euler.project

import com.vtech.euler.project.Problem5.gcd
import com.vtech.euler.project.Problem5.gcdRec
import com.vtech.euler.project.Problem5.lcmRec
import com.vtech.euler.project.Problem5.smallestDivisibleBy

object Problem5 {
    fun gcd(a: Long, b: Long): Long {
        var x = a
        var y = b
        while (y != 0L) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

    fun lcm(a: Long, b: Long): Long {
        return (a / gcd(a, b)) * b  // divide first to avoid overflow
    }

    fun smallestDivisibleBy(range: IntRange): Long {
        return range.fold(1L) { acc, n -> lcm(acc, n.toLong()) }
    }

    tailrec fun gcdRec(a: Long, b: Long): Long {
        return if (b == 0L) a else gcdRec(b, a % b)
    }

    fun lcmRec(a: Long, b: Long): Long {
        return a / gcdRec(a, b) * b  // divide before multiplying to avoid overflow
    }
}

fun main() {
//    println(smallestDivisibleBy(1..20))
//    println(gcd(6, 12))
//    println(3.mod(2))
//    println(gcdRec(18, 30))
    println(lcmRec(18, 30))
}