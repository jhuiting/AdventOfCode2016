package day4

import util.InputUtils

data class Room(val rawInput: String, val letters: String, val sectorId: Int, val checksum: String) {

    fun hasValidChecksum(): Boolean {
        fun getSortedLetterList(input: String): List<Pair<Char, Int>> {
            return input.toCharArray()
                    .filter(Char::isLetter)
                    .groupBy(Char::toChar)
                    .mapValues { it.value.count() }
                    .toList()
                    .sortedByDescending { it.second }
        }

        return getSortedLetterList(rawInput).take(5).map { it.first }.sorted() == checksum.toCharArray().sorted()
    }

    val decryptedName by lazy {
        letters.split("-").map { it.toCharArray().map { (it.toInt() + sectorId % 26).toChar() }.joinToString("") }
    }

    companion object {
        private val INPUT_PATTERN = Regex("^([a-z-]+)-(\\d+)\\[([a-z]{5})\\]$")

        fun fromEncryptedName(encryptedName: String): Room {
            val values = INPUT_PATTERN.matchEntire(encryptedName)?.groupValues!!

            return Room(values[0], values[1], values[2].toInt(), values[3])
        }
    }
}

fun solvePartOne(rooms: List<Room>) {
    val sectorSum = rooms.filter(Room::hasValidChecksum).sumBy(Room::sectorId)

    println("Total sum of sectorIds is: $sectorSum")
}

fun solvePartTwo(rooms: List<Room>) {
    val sectorId = rooms.filter { it.decryptedName.toString().contains("northpol") }
            .map { it.sectorId}

    println("The sector id with a decrypted name containing northpol is: $sectorId")
}

fun main(args: Array<String>) {

    val allRooms = InputUtils().getInputAsLineArray("day4").map { Room.fromEncryptedName(it) }

    solvePartOne(allRooms)
    solvePartTwo(allRooms)

//    println(Room.fromEncryptedName("aaaaa-bbb-z-y-x-123[abxyz]").hasValidChecksum())
//    println(Room.fromEncryptedName("a-b-c-d-e-f-g-h-987[abcde]").hasValidChecksum()) //valid
//    println(Room.fromEncryptedName("not-a-real-room-404[oarel]").hasValidChecksum()) // should be valid
//    println(Room.fromEncryptedName("totally-real-room-200[decoy]").hasValidChecksum()) //invalid
}
