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
            return Triangle(values[0].trim().toInt(), values[1].trim().toInt(), values[2].trim().toInt())
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

    val possibleTriangles = possibleTrianglesByIndex(0) + possibleTrianglesByIndex(1) + possibleTrianglesByIndex(2)
    println("Amount of possible vertical triangleInputs is: $possibleTriangles")
}

fun main(args: Array<String>) {
    val trianglesInput = InputUtils().getInputAsText("src/day3/input.txt").readText().split("\n")
            .map {it.split("  ").filter(String::isNotEmpty)}

    solvePartOne(trianglesInput)
    solvePartTwo(trianglesInput)
}