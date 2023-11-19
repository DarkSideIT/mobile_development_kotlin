package com.example.randomfilm

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.randomfilm.ui.theme.Film
import com.google.gson.Gson
import com.example.randomfilm.ui.theme.RandomFilmTheme
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    private lateinit var films: List<Film>
    private lateinit var listOfFilms: MutableList<Film>
    private lateinit var txt: TextView

    private fun displayFilm(film: Film) {
        val genre = if (film.genre != null) film.genre.joinToString(", ") else "N/A"
        val actors = if (film.actors != null) film.actors.joinToString(", ") else "N/A"
        val features = if (film.features != null) film.features.joinToString(", ") else "N/A"


        val filmDetails = """
            Название: ${film.title}
            Год выпуска: ${film.year}
            Режиссер: ${film.director}
            Жанр: $genre
            Актеры: $actors
            Рейтинг: ${film.rating}
            Характеристики: $features
        """.trimIndent()

        txt.text = filmDetails
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFile = resources.openRawResource(R.raw.films)
        val jsonReader = InputStreamReader(jsonFile)
        val filmType = object : TypeToken<List<Film>>() {}.type
        films = Gson().fromJson(jsonReader, filmType)
        listOfFilms = films.toMutableList()

        val button = findViewById<Button>(R.id.btn)
        val reset = findViewById<Button>(R.id.reset)
        txt = findViewById(R.id.text) // Assuming txt is the ID of your TextView

        button.setOnClickListener(View.OnClickListener {
            if (listOfFilms.size > 0) {
                val randomFilm = listOfFilms.random()
                displayFilm(randomFilm)
                listOfFilms.remove(randomFilm)
            } else {
                txt.text = "Фильмы закончились"
            }
        })

        reset.setOnClickListener(View.OnClickListener {
            listOfFilms = films.toMutableList()
            txt.text = "Случайный фильм"
        })
    }
}


