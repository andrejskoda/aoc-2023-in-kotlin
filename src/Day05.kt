fun main() {

    val input = readInput("Day05")
    val seeds = input[0]
        .substringAfter(":")
        .split(' ')
        .filter { it.isNotBlank() }
        .map { it.toLong() }


    val maps = input.drop(2)
        .fold(mutableListOf(mutableListOf<String>())) { acc, string ->
            if (string.isBlank())
                acc.add(mutableListOf())
            else
                acc.last().add(string)
            acc
        }.map {
            val (from, _, to) = it.first().split('-', ' ')
            val blocks = it.drop(1).map {
                val (dst, src, length) = it.split(' ').map { it.toLong() }
                Block(src, dst, length)
            }
            SeedsMap(from, to, blocks)
        }

    fun convert(cn: String, x: Long): Pair<String, Long>? {
        val seedsMap = maps.find { it.from == cn } ?: return null

        val block = seedsMap.blocks.find { x in it.src..<it.src + it.length }

        if (block != null) {
            return seedsMap.to to (x - block.src + block.dst)
        } else {
            return seedsMap.to to x
        }
    }

    val result = seeds.minOfOrNull { seed ->
        var cn = "seed"
        var x = seed

        while (true) {
            val result = convert(cn, x) ?: break
            cn = result.first
            x = result.second
        }
        check(cn == "location")
        x
    }

    println(result)
}


data class Block(val src: Long, val dst: Long, val length: Long)
data class SeedsMap(val from: String, val to: String, val blocks: List<Block>)

