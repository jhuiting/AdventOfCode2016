package day5

import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*

val md = MessageDigest.getInstance("MD5")

fun solvePartOne(input: String) {
    // Replace with foreach and return when completed
    val password = (0..15000000)
            .filter { getHash(input.plus(it)).startsWith("00000") }
            .map { getHash(input.plus(it))[5] }
            .take(8)
            .joinToString("")

    println("The password for doorId $input is $password")
}

fun solvePartTwo(input: String) {
    // Replace with foreach and return when completed
    val res = CharArray(8)

    (0..150000000)
            .filter { getHash(input.plus(it)).startsWith("00000") }
            .map { Pair(getHash(input.plus(it))[5].toInt() - 48, getHash(input.plus(it))[6]) }
            .filter { it.first < 8}
            .take(8)
            .forEach { res[it.first] = it.second }

    println(res.joinToString(""))
}

fun getHash(md5: String): String {

    md.update(md5.toByteArray(Charset.forName("UTF-8")))
    return String.format(Locale.ROOT, "%032x", BigInteger(1, md.digest()))
}

fun main(args: Array<String>) {
    solvePartOne("ugkcyxxp")
    solvePartTwo("ugkcyxxp")
}
