package com.vtech.euler.project

import com.vtech.euler.project.Problem18.inputNum
import com.vtech.euler.project.Problem18.inputTest
import com.vtech.euler.project.Problem18.maximumPathSum
import com.vtech.euler.project.Problem18.parseInput

object Problem18 {
    val inputTest = """
        3
        7 4
        2 4 6
        8 5 9 3
    """.trimIndent()
    val inputNum = """
        75
        95 64
        17 47 82
        18 35 87 10
        20 04 82 47 65
        19 01 23 75 03 34
        88 02 77 73 07 63 67
        99 65 04 28 06 16 70 92
        41 41 26 56 83 40 80 70 33
        41 48 72 33 47 32 37 16 94 29
        53 71 44 65 25 43 91 52 97 51 14
        70 11 33 28 77 73 17 78 39 68 17 57
        91 71 52 38 17 14 91 43 58 50 27 29 48
        63 66 04 68 89 53 67 30 73 16 69 87 40 31
        04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
    """.trimIndent()
    private fun parseInput(input: String): Sequence<List<Int>> =
        input.lineSequence().map { line -> line.trim().splitToSequence("\\s+".toRegex()).map { it.trim().toInt() }.toList() }
    fun maximumPathSum(input: String): List<Pair<Int, Int>> =
        parseInput(input).fold(-1 to mutableListOf<Pair<Int, Int>>()){ acc, list ->
            acc.first.let { index ->
                when(index) {
                    -1 -> { acc.second.add(index.inc() to list.first()); index.inc() }
                    else -> (if(list[index] > list[index.inc()]) index
                        else index.inc()).also { nIndex -> acc.second.add(nIndex.inc() to list[nIndex]) }
                } } to acc.second
        }.second
}

fun main() {
    println(maximumPathSum(inputTest))
    println(maximumPathSum(inputNum))
}