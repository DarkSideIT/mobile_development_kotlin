import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import com.github.doyaaaaken.kotlin.csv
import com.google.gson.Gson


data class AppData(
    val app: String,
    val category: String,
    val rating: Double,
    val reviews: Int,
    val size: String,
    val installs: Int,
    val type: String,
    val price: Boolean,
    val contentRating: String,
    val genres: List<String>,
    val lastUpdated: String,
    val currentVer: String,
    val androidVer: Int,
)


fun parseInstalls(installs: String):Int {
    return installs.replace("[^//d]".toRegex(), "").toInt()
}


fun parsePrice(price: String): Boolean{
    return price != "0"
}


fun parseGenres(genres: String): List<String>{
    return genres.split(";")
}


fun formatDate(date: String): String{
    val inputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    val parsedDate = inputFormat.parse(date)
    return outputFormat.format(parsedDate)
}


fun parseAndroidVersion(androidVer: String): Int {
    val versionRegex = "\\d+".toRegex()
    val versionDigits = versionRegex.findAll(androidVer).map { it.value.toInt() }
    return if (versionDigits.count() >= 2) {
        versionDigits.first() * 10 + versionDigits.last()
    } else {
        versionDigits.first()
    }
}


fun main(args: Array<String>) {
    val csvFile = File("src/main/resources/googleplaystore.csv")
    val appsList = mutableListOf<AppData>()


    csvReader().readAll(csvFile) {
        readAllAsSequence().forEach { row ->
            val appData = AppData(
                app = row[0],
                category = row[1],
                rating = row[2].toDouble(),
                reviews = row[3].toInt(),
                size = row[4],
                installs = parseInstalls(row[5]),
                type = row[6],
                price = parsePrice(row[7]),
                contentRating = row[8],
                genres = parseGenres(row[9]),
                lastUpdated = formatDate(row[10]),
                currentVer = row[11],
                androidVer = parseAndroidVersion(row[12])
            )
            appsList.add(appData)
        }
    }

    val gson = Gson()
    val json = gson.toJson(appsList)

    File("output.json").writeText(json)
}
