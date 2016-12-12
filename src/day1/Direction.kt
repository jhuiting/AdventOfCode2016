package day1

import day1.Direction.*

enum class Direction (val value: Int) {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    companion object {
        private val directionValues = values().associateBy(Direction::value)

        fun fromInt(intVal: Int): Direction {
            return directionValues[intVal] as Direction
        }
    }
}

enum class Turn(val value: Int) {
    LEFT(-1), RIGHT(1)
}

data class State (val facing: Direction = NORTH, val x: Int = 0, val y: Int = 0) {
    val distance by lazy { Math.abs(x) + Math.abs(y) }
    val coordinates by lazy { Pair(x, y) }
}

data class Step (val turn: Turn, val dist: Int) {
    companion object {
        fun fromString(step: String): Step {

            if (step.startsWith('L')) {
                return Step(Turn.LEFT, step.substring(1).toInt())
            }

            return Step(Turn.RIGHT, step.substring(1).toInt())
        }
    }
}
