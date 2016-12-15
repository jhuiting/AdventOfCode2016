package day1

import util.InputUtils
import day1.Direction.*

fun move(state: State, step: Step): State {
    val direction = Direction.fromInt(((state.facing.value + 4) + step.turn.value) % 4)

    return when (direction) {
        NORTH -> state.copy(facing = direction, x = state.x + step.dist)
        EAST -> state.copy(facing = direction, y = state.y + step.dist)
        SOUTH -> state.copy(facing = direction, x = state.x - step.dist)
        WEST -> state.copy(facing = direction, y = state.y - step.dist)
    }
}

fun main(args: Array<String>) {
    val directions = InputUtils().getInputAsLineArray("day1")

    //Assignment 1.1
    val state = directions.fold(State()) {
        currentState, step -> move(currentState, Step.fromString(step.trim()))
    }

    println("Easter Bunny HQ is ${Math.abs(state.distance)} blocks away")

    //Assignment 1.2

    //Poor man's scanLeft, not implemented in stdLib of Kotlin
    fun <T : Any, R : Any> Iterable<T>.scanLeft(initial: R, operation: (R, T) -> R): List<R> {
        val result = arrayListOf(initial)
        forEach { result.add(operation(result.last(), it)) }
        return result
    }

    val coordinates = arrayListOf<Pair<Int, Int>>()

    directions.scanLeft(State()) {
        currentState: State, step: String ->
        move(currentState, Step.fromString(step.trim()))
    }.forEach {
        if (coordinates.contains(it.coordinates)) {
            println("Found the real HQ location for Easter Bunny at ${it.coordinates}, that's ${it.distance} blocks away")
            return
        }

        coordinates.add(it.coordinates)
    }
}
