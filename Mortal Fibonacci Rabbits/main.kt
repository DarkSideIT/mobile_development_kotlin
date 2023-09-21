fun main() {
    val n = 85
    val m = 19
    val bunnies = Array<ULong>(n) { 1u }

    for (month in 2 until n) {
        bunnies[month] =
            if (month < m) bunnies[month - 2] + bunnies[month - 1]
            else if (month == m || month == m + 1) bunnies[month - 2] + bunnies[month - 1] - 1u
            else bunnies[month - 2] + bunnies[month - 1] - bunnies[month - m - 1]
    }

    println(bunnies.last())
}
