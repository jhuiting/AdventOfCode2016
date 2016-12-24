package day8

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
    val allRects = InputUtils().getInputAsLineArray("day8")
            .filter { it.startsWith("rect") }
            .map { Rect.fromString(it) }

//    val even: IntArray = intArrayOf(2, 4, 6)
//    val odd: IntArray = intArrayOf(1, 3, 5)
//
//    val lala: Array<IntArray> = arrayOf(even, odd)

    /**
     *  Array contents:
     *  246
     *  135
     */
    println("8.1 - Amount of pixels lit is: ${allRects.sumBy(Rect::size)}")
}