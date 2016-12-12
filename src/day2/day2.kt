package day2

import util.InputUtils

data class KeyPad(var value: Int) {
        /**
         * Keypad
         *  1 2 3
         *  4 5 6
         *  7 8 9
         */
        fun enterCode(move: Move): KeyPad = when(move) {
            Move.UP -> if (!listOf(1,2,3).contains(value)) copy(value = value + move.intValue) else this
            Move.DOWN -> if (!listOf(7,8,9).contains(value)) copy(value = value + move.intValue) else this
            Move.LEFT -> if (!listOf(1,4,7).contains(value))  copy(value = value + move.intValue) else this
            Move.RIGHT -> if (!listOf(3,6,9).contains(value)) copy(value = value + move.intValue) else this
        }

        /**
                1
              2 3 4
            5 6 7 8 9
              A B C
                D
         */
        fun enterCodeAdvancedKeyPad(move: MovePartTwo): KeyPad = when(move) {
            MovePartTwo.UP -> if (!listOf(1,2,4,5,9).contains(value)) copy(value = value + move.intValue) else this
            MovePartTwo.DOWN -> if (!listOf(5,9, 10, 12, 15).contains(value)) copy(value = value + move.intValue) else this
            MovePartTwo.LEFT -> if (!listOf(1,2,5, 10, 15).contains(value))  copy(value = value + move.intValue) else this
            MovePartTwo.RIGHT -> if (!listOf(1,4,9, 12, 15).contains(value)) copy(value = value + move.intValue) else this
        }
}

enum class MovePartTwo(val intValue: Int, val stringValue: String) {
    LEFT(-1, "L"), RIGHT(1, "R"), UP(-4, "U"), DOWN(4, "D");

    companion object {
        private val moveValues =  MovePartTwo.values().associateBy(MovePartTwo::stringValue)

        fun fromString(move: String): MovePartTwo = moveValues[move] as MovePartTwo
    }
}

enum class Move(val intValue: Int, val stringValue: String) {
    LEFT(-1, "L"), RIGHT(1, "R"), UP(-3, "U"), DOWN(3, "D");

    companion object {
        private val moveValues =  Move.values().associateBy(Move::stringValue)

        fun fromString(move: String): Move = moveValues[move] as Move
    }
}

fun main(args: Array<String>) {
    val buttonInstructions = InputUtils().getInputAsText("src/day2/input.txt").readText().split("\n")
            .map{ it.toCharArray().map { Move.fromString(it.toString()) }}

    //Assignment 2.1
    var keyPad = KeyPad(5)
    buttonInstructions.forEach {
        it.map {
            keyPad = keyPad.enterCode(it)
        }
//        println(keyPad)
    }


    //Assignment 2.1
    val buttonInstructions2 = listOf("ULL", "RRDD", "LURDL", "UUUUD")
            .map{ it.toCharArray().map { MovePartTwo.fromString(it.toString()) }}

    var secondKeyPad = KeyPad(5)
    buttonInstructions2.forEach {
        it.map {
            secondKeyPad = secondKeyPad.enterCodeAdvancedKeyPad(it)
        }
        println(secondKeyPad)
    }
}