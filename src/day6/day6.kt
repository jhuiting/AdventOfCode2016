package day6

import util.InputUtils

fun getLetterPairs(input: CharArray): List<Pair<Char, Int>> {
    return input.filter(Char::isLetter)
            .groupBy(Char::toChar)
            .mapValues { it.value.count() }
            .toList()
}

fun getMostCommonCharacterList(input: CharArray): List<Char> {
    return getLetterPairs(input)
            .sortedByDescending { it.second }
            .take(1)
            .map {it.first}
}

fun getLeastCommonCharacterList(input: CharArray): List<Char> {
    return getLetterPairs(input)
            .sortedBy { it.second }
            .take(1)
            .map {it.first}
}

fun main(args: Array<String>) {
    val allMessages = InputUtils().getInputAsText("src/day6/test-input.txt").readText().split("\n")

    solvePartOne(allMessages)
    solvePartTwo(allMessages)
}

private fun solvePartOne(allMessages: List<String>) {
    val message = (0..allMessages[0].length - 1)
            .flatMap { i -> getMostCommonCharacterList(allMessages.map { it[i] }.toCharArray()) }
            .joinToString("")

    println("6.1 - Decoded message is $message")
}


private fun solvePartTwo(allMessages: List<String>) {
    val message = (0..allMessages[0].length - 1)
            .flatMap { i -> getLeastCommonCharacterList(allMessages.map { it[i] }.toCharArray()) }
            .joinToString("")

    println("6.2 - Decoded message is $message")
}
