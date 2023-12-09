fun main() {
    fun getToMap(
        destinationRangeStart: Int,
        sourceRangeStart: Int,
        rangeLength: Int
    ) = (0..<rangeLength).associate { Pair(sourceRangeStart + it, destinationRangeStart + it) }

    fun sourceToDestinationMap(triples: List<Triple<Int, Int, Int>>) = triples
        .map { getToMap(it.first, it.second, it.third) }
        .reduce { acc, map -> acc + map }
        .toSortedMap()

    fun List<Triple<Int, Int, Int>>.sourceToDestinationMap() = this.map { getToMap(it.first, it.second, it.third) }
        .reduce { acc, map -> acc + map }
        .toSortedMap()

    fun part1(input: List<String>): Int {
        val seeds = listOf(79, 14, 55, 13)
        val seedToSoil = listOf(
            Triple(50, 98, 2),
            Triple(52, 50, 48)
        )
        val soilToFertilizer = listOf(
            Triple(0, 15, 37),
            Triple(37, 52, 2),
            Triple(39, 0, 15),
        )
        val fertilizerToWater = listOf(
            Triple(49, 53, 8),
            Triple(0, 11, 42),
            Triple(42, 0, 7),
            Triple(57, 7, 4),
        )
        val waterToLight = listOf(
            Triple(88, 18, 7),
            Triple(18, 25, 70),
        )
        val lightToTemperature = listOf(
            Triple(45, 77, 23),
            Triple(81, 45, 19),
            Triple(68, 64, 13),
        )
        val temperatureToHumidity = listOf(
            Triple(0, 69, 1),
            Triple(1, 0, 69),
        )
        val humidityToLocation = listOf(
            Triple(60, 56, 37),
            Triple(56, 93, 4),
        )

        val seedToSoilMap = sourceToDestinationMap(seedToSoil)
        val soilToFertilizerMap = sourceToDestinationMap(soilToFertilizer)
        val fertilizerToWaterMap = sourceToDestinationMap(fertilizerToWater)
        val waterToLightMap = sourceToDestinationMap(waterToLight)
        val lightToTemperatureMap = sourceToDestinationMap(lightToTemperature)
        val temperatureToHumidityMap = sourceToDestinationMap(temperatureToHumidity)
        val humidityToLocationMap = sourceToDestinationMap(humidityToLocation)



        return seeds
            .asSequence()
            .map { seedToSoilMap.getOrDefault(it, it) }
            .map { soilToFertilizerMap.getOrDefault(it, it) }
            .map { fertilizerToWaterMap.getOrDefault(it, it) }
            .map { waterToLightMap.getOrDefault(it, it) }
            .map { lightToTemperatureMap.getOrDefault(it, it) }
            .map { temperatureToHumidityMap.getOrDefault(it, it) }
            .map { humidityToLocationMap.getOrDefault(it, it) }
            .toList()
            .min()
    }


    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35)
    val input = readInput("Day05")
//    part1(input).println()
//    part2(input).println()
}
