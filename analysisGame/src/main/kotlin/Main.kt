import java.io.FileInputStream
import java.util.*



fun readRoles(scanner: Scanner, rolesMap: MutableMap<String, MutableList<String>>) {
    scanner.nextLine()

    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if ("textLines" in line) {
            break
        }
        rolesMap[line] = mutableListOf()
    }
}

fun readLines(scanner: Scanner, rolesMap: MutableMap<String, MutableList<String>>) {
    var lineNumber = 1

    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        val (role, text) = line.split(':', limit = 2)
        rolesMap.computeIfAbsent(role) { mutableListOf() }.add("$lineNumber) $text")
        lineNumber++
    }
}

fun printLines(rolesMap: Map<String, List<String>>) {
    for ((role, lines) in rolesMap) {
        val formattedLines = lines.joinToString("\n")
        println("$role:\n$formattedLines\n")
    }
}
fun main() {
    val inputFile = "src/main/kotlin/res/textLines.txt"
    val sc = Scanner(FileInputStream(inputFile))
    val rolesMap = mutableMapOf<String, MutableList<String>>()
    readRoles(sc, rolesMap)
    readLines(sc, rolesMap)
    printLines(rolesMap)
}