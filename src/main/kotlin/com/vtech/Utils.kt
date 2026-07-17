@file:Suppress("unused", "LocalVariableName")

package com.vtech

inline fun <R> Number.fold(initial: R, operation: (acc: R, Number, Int) -> R): R =
    (initial to (this.toLong() to 0)).let { (accumulator, quotient) ->
        val ZERO = 0; val TEN = 10
        var tq = quotient
        var result = accumulator
        while ((tq.first / TEN).let { (it to (tq.first % TEN).toInt()).also { p -> tq = p } }.let { p -> p.first > ZERO || p.second > ZERO })
            result = operation(result, tq.first, tq.second)
        return result
    }

inline fun <R> IntArray.combination(operation: (firstIndexAndValue: Pair<Int, Int>, secondIndexAndValue: Pair<Int, Int>) -> R): List<R> =
    this.foldIndexed(mutableListOf()) { fIndex, fAcc, fValue ->
        this.filter { it > fIndex }.foldIndexed(0) { sIndex, sAcc, sValue ->
            fAcc.add(operation(fIndex to fValue, sIndex to sValue)).let { sAcc }
        }.let { fAcc }
    }

inline fun <R> IntArray.combination(operation: (firstIndexAndValue: Pair<Int, Int>, secondIndexAndValue: Pair<Int, Int>, thirdIndexAndValue: Pair<Int, Int>) -> R): List<R> =
    this.foldIndexed(mutableListOf()) { fIndex, fAcc, fValue ->
        this.filter { it > fIndex }.foldIndexed(0) { sIndex, sAcc, sValue ->
            this.filter { it > sIndex }.foldIndexed(0) { tIndex, tAcc, tValue ->
                    fAcc.add(operation(fIndex to fValue, sIndex to sValue, tIndex to tValue)).let { tAcc }
                }.let { sAcc }
        }.let { fAcc }
    }

inline fun <T, R> Sequence<T>.foldWhile(
    initial: R,
    predicate: (acc: R, T) -> Boolean,
    operation: (acc: R, T) -> R
): R {
    var accumulator = initial
    for (element in this) {
        if (!predicate(accumulator, element)) break
        accumulator = operation(accumulator, element)
    }
    return accumulator
}