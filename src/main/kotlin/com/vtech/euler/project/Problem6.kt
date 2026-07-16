package com.vtech.euler.project

import com.vtech.euler.project.Problem6.findDiff

object Problem6 {
    fun findSum(range: LongRange): Long =
        range.first.dec().let { first ->
            first.times(first.inc()).div(2).let { leftSum ->
                range.last.let { last -> last.times(last.inc()).div(2) - leftSum }
            }
        }

    fun naturalNumberSquareSum(range: LongRange): Long = range.fold(0L) { acc, lng -> acc + (lng * lng) }
    fun naturalNumberSumSquare(range: LongRange): Long = findSum(range).let { it * it }
    fun findDiff(range: LongRange): Long = naturalNumberSumSquare(range) - naturalNumberSquareSum(range)
}

fun main() {
//    println(findSum(4L..10L))
    println(findDiff(1L..100L))
}