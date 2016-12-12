package day3

import util.InputUtils

data class Triangle (val edgeOne: Int, val edgeTwo: Int, val edgeThree: Int) {
    val isPossible by lazy  {
        edgeOne + edgeTwo > edgeThree &&
        edgeOne + edgeThree > edgeTwo &&
        edgeTwo + edgeThree > edgeOne
    }
}

fun solvePartOne() {
    val triangles = InputUtils().getInputAsText("src/day3/input.txt").readText().split("\n")
            .map { it.split("  ").filter(String::isNotEmpty).map{ it.trim().toInt() } }
            .map { Triangle(it[0], it[1], it[2])}

    println("Amount of possible triangles is: ${triangles.filter { it.isPossible }.count()}")
}

fun main(args: Array<String>) {
    solvePartOne()
}