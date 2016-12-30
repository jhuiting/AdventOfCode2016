package day9

import util.InputUtils

data class Rect(val width: Int, val height: Int) {
    val size by lazy { width * height }

    companion object {
        private val RECT_PATTERN = Regex("^rect (\\d+)x(\\d+)$")

        fun fromString(rect: String): Rect {
            val matchRectSize = RECT_PATTERN.matchEntire(rect)!!
            return Rect(matchRectSize.groupValues[1].toInt(), matchRectSize.groupValues[2].toInt())
        }
    }
}

fun main(args: Array<String>) {
    InputUtils().getInputAsString("day9")
            .split(Regex("\\("))
            .filter(String::isNotEmpty)
            .map {
                println("(" + it)
            }

//    val even: IntArray = intArrayOf(2, 4, 6)
//    val odd: IntArray = intArrayOf(1, 3, 5)
//
//    val lala: Array<IntArray> = arrayOf(even, odd)

    /**
     *  Array contents:
     *  246
     *  135
     */
}