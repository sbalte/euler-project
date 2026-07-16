package com.vtech.euler.project

import com.vtech.euler.project.Problem14.MAX_NUM_LIMIT
import com.vtech.euler.project.Problem14.MIN_NUM_LIMIT
import com.vtech.euler.project.Problem14.longestCollatzSequence
import com.vtech.foldWhile

object Problem14 {
    const val MAX_NUM_LIMIT = 1_000_000U
    const val MIN_NUM_LIMIT = 13U
    fun longestCollatzSequence(limit: Pair<UInt, UInt>): Pair<UInt, List<UInt>> =
        (limit.first..limit.second)
            .fold(1U to emptyList()) { acc, num ->
                (1U..UInt.MAX_VALUE).asSequence().foldWhile(
                    num to mutableListOf(num),
                    { iAcc, _ -> iAcc.first > 1U }) { iAcc, _ ->
                    iAcc.first.let { cSeq ->
                        (if (cSeq.mod(2U) == 0U) cSeq.div(2U) else 3U.times(cSeq) + 1U)
                            .let { result -> iAcc.second += result; result to iAcc.second }
                    }
                }.let { result ->
//                    println(">>>>${result.second.first()} Collatz Sequence w/ size ${result.second.size} is: ${result.second}")
                    if(result.second.size > acc.second.size) {
                        (result.second.first() to result.second).also {
                            println("Found bigger one: ${it.first} Collatz Sequence w/ max size ${it.second.size} is: ${it.second}")
                        }
                    } else acc }
            }
}

fun main() = println(longestCollatzSequence(MIN_NUM_LIMIT to MAX_NUM_LIMIT).let { ">>>>${it.first} Collatz Sequence w/ max size ${it.second.size} upto million is: ${it.second}" })