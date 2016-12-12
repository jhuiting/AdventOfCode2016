package day3

import util.InputUtils

data class Triangle (val edgeOne: Int, val edgeTwo: Int, val edgeThree: Int) {
    val isPossible by lazy  {
        edgeOne + edgeTwo > edgeThree &&
        edgeOne + edgeThree > edgeTwo &&
        edgeTwo + edgeThree > edgeOne
    }
}

fun <T> Sequence<T>.batch(n: Int): Sequence<List<T>> {
    return BatchingSequence(this, n)
}

private class BatchingSequence<T>(val source: Sequence<T>, val batchSize: Int) : Sequence<List<T>> {
    override fun iterator(): Iterator<List<T>> = object : AbstractIterator<List<T>>() {
        val iterate = if (batchSize > 0) source.iterator() else emptyList<T>().iterator()
        override fun computeNext() {
            if (iterate.hasNext()) setNext(iterate.asSequence().take(batchSize).toList())
            else done()
        }
    }
}

fun checkTriangles(triangles: List<List<Int>>): Int {
    fun possibleTrianglesByIndex(index: Int): Int {
        return triangles.flatMap {it.slice(listOf(index))}.asSequence().batch(3)
                  .map { Triangle(it[0], it[1], it[2])}.toList()
                  .filter { it.isPossible }
                  .count()
    }

    return possibleTrianglesByIndex(0) + possibleTrianglesByIndex(1) + possibleTrianglesByIndex(2)
}


fun solvePartOne() {
    val triangles = InputUtils().getInputAsText("src/day3/input.txt").readText().split("\n")
            .map { it.split("  ")
            .filter(String::isNotEmpty) }
            .map { Triangle(Integer.parseInt(it[0]), Integer.parseInt(it[1]), Integer.parseInt(it[2]))}

    println("Amount of possible triangles is: ${triangles.filter { it.isPossible }.count()}")
}

fun solvePartTwo() {
    val triangles = InputUtils().getInputAsText("src/day3/input.txt").readText().split("\n")
            .map { it.split("  ")
                    .filter(String::isNotEmpty)
                    .map{ it.trim().toInt() }}

    println("Amount of possible vertical triangles is: ${checkTriangles(triangles)}")
}

fun main(args: Array<String>) {
    solvePartOne()
    solvePartTwo()
}