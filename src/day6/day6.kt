package day6

import util.InputUtils

fun getMostCommonCharacter(input: CharArray): Char = getLetterPairs(input).sortedByDescending { it.second }[0].first
fun getLeastCommonCharacter(input: CharArray): Char = getLetterPairs(input).sortedBy { it.second }[0].first

fun getLetterPairs(input: CharArray): List<Pair<Char, Int>> {
    return input.groupBy(Char::toChar).mapValues { it.value.count() }.toList()
}

fun decodeMessage(allMessages: List<String>, charProcessor: (m: CharArray) -> Char): String {
    return (0..allMessages[0].length - 1)
            .map { i -> charProcessor(allMessages.map { it[i] }.toCharArray()) }
            .joinToString("")
}

fun main(args: Array<String>) {
    val allMessages = InputUtils().getInputAsLineArray("day6")

    println("6.1 - Decoded message is ${decodeMessage(allMessages, ::getMostCommonCharacter)}")
    println("6.2 - Decoded message is ${decodeMessage(allMessages, ::getLeastCommonCharacter)}")
}