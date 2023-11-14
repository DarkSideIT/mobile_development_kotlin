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
import com.example.randomfilm.ui.theme.RandomFilmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btn)
        val reset = findViewById<Button>(R.id.reset)
        var txt = findViewById<TextView>(R.id.text)
        val films: Array<String> = resources.getStringArray(R.array.RandomFilms)
        var listOfFilms: MutableList<String> = films.toMutableList()


        button.setOnClickListener(View.OnClickListener {
            if (listOfFilms.size > 0){
                val randomFilm = listOfFilms.random()
                txt.text = randomFilm
                listOfFilms.remove(randomFilm)
            }
            else {
                txt.text = "Фильмы закончились"
            }
        })
        reset.setOnClickListener(View.OnClickListener {
            listOfFilms = films.toMutableList()
            txt.text = "Случайный фильм"
        })
    }
}

