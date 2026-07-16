package com.vtech.euler.project

import com.vtech.euler.project.Problem9.findPyTriplet
import com.vtech.euler.project.Problem9.isPythagoreanNumbers
import com.vtech.foldWhile

object Problem9 {
    const val pyNumSum = 1000
    fun Int.square(): Int = this * this
    fun isPythagoreanNumbers(triplet: Triple<Int, Int, Int>): Boolean = triplet.first.square() + triplet.second.square() == triplet.third.square()
    fun findPyTriplet(): Triple<Int, Int, Int> =
        (1..pyNumSum.div(2)).asSequence().foldWhile(true to Triple(1, 1, pyNumSum.minus(2)),
            { acc, _ -> acc.first }) { acc, oItem ->
            (oItem..(pyNumSum - oItem)).asSequence()
                .foldWhile(acc, { iAcc, _ -> iAcc.first }) { _, iItem ->
                    Triple(oItem, iItem, pyNumSum.minus(oItem + iItem)).let {
                        !isPythagoreanNumbers(it) to it

                    }
                }
        }.second
}

fun main() {
    println(findPyTriplet().let {
        """
            Pythagorean triplet ${it.first}, ${it.second}, and ${it.third}. 
            Is Pythagorean test passed ${isPythagoreanNumbers(it)}
            and their sum is ${it.first + it.second + it.third}
        """.trimIndent() })
}