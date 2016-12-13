package day3

import util.InputUtils
import util.batch

data class Triangle (val edgeOne: Int, val edgeTwo: Int, val edgeThree: Int) {
    val isPossible by lazy  {
        edgeOne + edgeTwo > edgeThree &&
        edgeOne + edgeThree > edgeTwo &&
        edgeTwo + edgeThree > edgeOne
    }

    companion object {
        fun fromList(values: List<String>): Triangle {
            fun toInt(s: String) = s.trim().toInt()

            return Triangle(toInt(values[0]), toInt(values[1]), toInt(values[2]))
        }
    }
}

fun solvePartOne(triangleInputs: List<List<String>>) {
    val possibleTriangles = triangleInputs
            .map {Triangle.fromList(it)}
            .filter { it.isPossible }
            .count()

    println("Amount of possible triangleInputs is: $possibleTriangles")
}

fun solvePartTwo(triangleInputs: List<List<String>>) {
    /** Take all nth items from the list and check these triangleInputs **/
    fun possibleTrianglesByIndex(index: Int): Int {
        return triangleInputs.flatMap {it.slice(listOf(index))}.asSequence().batch(3)
                .map { Triangle.fromList(it)}.toList()
                .count {it.isPossible}
    }

    println("Amount of possible vertical triangleInputs is: ${listOf(0, 1, 2).fold(0) { i, j -> i + possibleTrianglesByIndex(j) }}")
}

fun main(args: Array<String>) {
    val trianglesInput = InputUtils().getInputAsText("src/day3/input.txt").readText().split("\n")
            .map {it.split("  ").filter(String::isNotEmpty)}

    solvePartOne(trianglesInput)
    solvePartTwo(trianglesInput)
}