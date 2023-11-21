package com.example.guessnumber

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.guessnumber.ui.theme.GuessNumberTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rangeBeginValue = findViewById<EditText>(R.id.rangeBegin)
        val rangeEndValue = findViewById<EditText>(R.id.rangeEnd)
        val buttonStart = findViewById<Button>(R.id.searchButton)

        buttonStart.setOnClickListener{
            val intent = Intent(this, Game::class.java)
            intent.putExtra("rangeBeginValue", rangeBeginValue.text.toString())
            intent.putExtra("rangeEndValue", rangeEndValue.text.toString())
            startActivity(intent)
        }



    }
}
