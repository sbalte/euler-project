package com.vtech.euler.project

import com.vtech.euler.project.Problem19.countingSundays
import java.time.LocalDate
import java.time.DayOfWeek

object Problem19 {
    fun countingSundays(): Int =
        (LocalDate.of(1901, 1, 1) to 100 * 12).let { (start, totalMonths) ->
            (0 until totalMonths)
                .map { start.plusMonths(it.toLong()) }
                .count { it.dayOfWeek == DayOfWeek.SUNDAY }
        }
}

fun main() = println("Number of Sundays falling on the 1st of the month: ${countingSundays()}")