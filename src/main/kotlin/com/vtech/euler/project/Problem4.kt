package com.vtech.euler.project

import com.vtech.euler.project.Problem4.palindromeNumber

object Problem4 {
    private fun isPalindrome(pair: Pair<Int, Int>): Boolean = pair.let {
        var (product, origProduct) = pair.first.times(pair.second).let { it to it }
        var result = 0
        while(product > 1) {
            result = result * 10 + product % 10
            product /= 10

        }

        return origProduct == result
    }
    fun palindromeNumber(numDigit: Int): Pair<Int, Int> {
        val input = (1..numDigit).fold(0) { acc, _ -> acc * 10 + 9 }
        var (found, isNum1Modify) = false to false
        var (num1, num2) = input to input
        var result = input to input
        while(!found && !(num1 < 1 || num2 < 1)) {
            found = isPalindrome((num1 to num2).also { result = it })
            if(isNum1Modify) num1--
            else num2--
            isNum1Modify = isNum1Modify.not()
        }
        return if(found) result
        else throw NoSuchElementException("palindrome not found")
    }
}

fun main() {
    println(palindromeNumber(3).let { "${it} and product palindrome ${it.first.times(it.second)}" })
}