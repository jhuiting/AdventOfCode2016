package day6

import util.InputUtils

fun getMostCommonCharacter(input: CharArray): Char = getLetterPairs(input).sortedByDescending { it.second }[0].first
fun getLeastCommonCharacter(input: CharArray): Char = getLetterPairs(input).sortedBy { it.second }[0].first

fun getLetterPairs(input: CharArray): List<Pair<Char, Int>> = input.groupBy(Char::toChar).mapValues { it.value.count() }.toList()

private fun solvePartOne(allMessages: List<String>) {
    val message = (0..allMessages[0].length - 1)
            .map { i -> getMostCommonCharacter(allMessages.map { it[i] }.toCharArray()) }
            .joinToString("")

    println("6.1 - Decoded message is $message")
}

private fun solvePartTwo(allMessages: List<String>) {
    val message = (0..allMessages[0].length - 1)
            .map { i -> getLeastCommonCharacter(allMessages.map { it[i] }.toCharArray()) }
            .joinToString("")

    println("6.2 - Decoded message is $message")
}

fun main(args: Array<String>) {
    val allMessages = InputUtils().getInputAsText("src/day6/input.txt").readText().split("\n")

    solvePartOne(allMessages)
    solvePartTwo(allMessages)
}
