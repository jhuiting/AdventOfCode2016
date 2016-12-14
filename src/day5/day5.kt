package day5

import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest
import java.util.*
import kotlin.system.measureTimeMillis

val md = MessageDigest.getInstance("MD5")

fun solvePartOne(input: String) {

    println(measureTimeMillis {
        val password = (0..10000000)
                .filter { getHash(input.plus(it)).startsWith("00000") }
                .map { getHash(input.plus(it))[5] }
                .take(8)
                .joinToString("")

        println("The password for doorId $input is $password")
    })
}

fun getHash(md5: String): String {
    md.update(md5.toByteArray(Charset.forName("UTF-8")))
    return String.format(Locale.ROOT, "%032x", BigInteger(1, md.digest()))
}

fun main(args: Array<String>) {
    solvePartOne("ugkcyxxp")
}
