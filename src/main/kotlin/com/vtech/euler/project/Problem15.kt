package com.vtech.euler.project

import com.vtech.euler.project.Problem15.ALLOWED_MOVE.DOWN
import com.vtech.euler.project.Problem15.ALLOWED_MOVE.RIGHT
import com.vtech.euler.project.Problem15.latticePaths
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.incrementAndFetch

object Problem15 {
    enum class ALLOWED_MOVE { DOWN, RIGHT }
    context(gridSize: Pair<UInt, UInt>, move: ALLOWED_MOVE)
    private fun canMove(currPoint: Pair<Int, Int>,): Boolean = when(move) {
            DOWN -> currPoint.second.toUInt() < gridSize.second
            RIGHT -> currPoint.first.toUInt() < gridSize.first
        }
    @OptIn(ExperimentalAtomicApi::class)
    context(gridSize: Pair<UInt, UInt>)
    private fun reachedEnd(currPoint: Pair<Int, Int>,): Boolean = currPoint.first.toUInt() == gridSize.first && currPoint.second.toUInt() == gridSize.second
    context(gridSize: Pair<UInt, UInt>, move: ALLOWED_MOVE)
    private fun nextMove(currPoint: Pair<Int, Int>): Pair<Int, Int>? = if(canMove(currPoint)) {
        when (move) {
            DOWN -> currPoint.first to currPoint.second.inc()
            RIGHT -> currPoint.first.inc() to currPoint.second
        }
    }else null
    @OptIn(ExperimentalAtomicApi::class)
    context(gridSize: Pair<UInt, UInt>, numRoutes: AtomicLong)
    fun recursiveRoute(currPoint: Pair<Int, Int>) {
        if (reachedEnd(currPoint)) numRoutes.incrementAndFetch()
        ALLOWED_MOVE.entries.forEach { move ->
            context(move) {
//                println("checking $move is possible ${canMove(currPoint)} w/ currentPosition $currPoint")
                nextMove(currPoint)?.let {
//                    println("trying next move $it w/ direction $move")
                    context(it, gridSize) { recursiveRoute(it) }
                } //?: println("skipping move after $currPoint for direction $move")
            }
        }
    }

    @OptIn(ExperimentalAtomicApi::class)
    context(gridSize: Pair<UInt, UInt>)
    fun latticePaths(currPoint: Pair<Int, Int>): Long {
        val numRoutes = AtomicLong(0)
        context(numRoutes) {
            recursiveRoute(currPoint)
        }
        return numRoutes.load()
    }
}

fun main() {
    /**
     * Solution: Num routes possible w/ grid size (20, 20) is: 137,846,528,820
     * It took 15h 10m 55s to finish 20 X 20 grid. Need a better solution [ parallelize ]
     */
    val GRID_SIZE = 10U to 10U
    context(GRID_SIZE) {
        println("Num routes possible w/ grid size $GRID_SIZE is: ${latticePaths(0 to 0,)}")
    }
}