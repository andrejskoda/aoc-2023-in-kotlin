fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val firstDigit = it.first { it.isDigit() }
            val lastDigit = it.last { it.isDigit() }
            "$firstDigit$lastDigit".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val list = input.map {
            val firstDigit = mapOf(
                1 to listOf(it.indexOf("one"), it.indexOf("1")).filter { it != -1 }.minOrNull(),
                2 to listOf(it.indexOf("two"), it.indexOf("2")).filter { it != -1 }.minOrNull(),
                3 to listOf(it.indexOf("three"), it.indexOf("3")).filter { it != -1 }.minOrNull(),
                4 to listOf(it.indexOf("four"), it.indexOf("4")).filter { it != -1 }.minOrNull(),
                5 to listOf(it.indexOf("five"), it.indexOf("5")).filter { it != -1 }.minOrNull(),
                6 to listOf(it.indexOf("six"), it.indexOf("6")).filter { it != -1 }.minOrNull(),
                7 to listOf(it.indexOf("seven"), it.indexOf("7")).filter { it != -1 }.minOrNull(),
                8 to listOf(it.indexOf("eight"), it.indexOf("8")).filter { it != -1 }.minOrNull(),
                9 to listOf(it.indexOf("nine"), it.indexOf("9")).filter { it != -1 }.minOrNull(),
            )
                .filter { it.value != null }
                .minByOrNull { it.value!! }
                ?.key
            val secondDigit = mapOf(
                1 to listOf(it.lastIndexOf("one"), it.lastIndexOf("1")).filter { it != -1 }.maxOrNull(),
                2 to listOf(it.lastIndexOf("two"), it.lastIndexOf("2")).filter { it != -1 }.maxOrNull(),
                3 to listOf(it.lastIndexOf("three"), it.lastIndexOf("3")).filter { it != -1 }.maxOrNull(),
                4 to listOf(it.lastIndexOf("four"), it.lastIndexOf("4")).filter { it != -1 }.maxOrNull(),
                5 to listOf(it.lastIndexOf("five"), it.lastIndexOf("5")).filter { it != -1 }.maxOrNull(),
                6 to listOf(it.lastIndexOf("six"), it.lastIndexOf("6")).filter { it != -1 }.maxOrNull(),
                7 to listOf(it.lastIndexOf("seven"), it.lastIndexOf("7")).filter { it != -1 }.maxOrNull(),
                8 to listOf(it.lastIndexOf("eight"), it.lastIndexOf("8")).filter { it != -1 }.maxOrNull(),
                9 to listOf(it.lastIndexOf("nine"), it.lastIndexOf("9")).filter { it != -1 }.maxOrNull(),
            )
                .filter { it.value != null }
                .maxByOrNull { it.value!! }
                ?.key
            if (firstDigit == null || secondDigit == null) {
                println("Input: $it, FirstDigit:$firstDigit, SecondDigit:$secondDigit")
            }
            "${firstDigit}$secondDigit".toInt()
        }

        return list.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
