package com.example.guessnumber

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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




class Game : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var rangeBeginValue: Int = 0
        var rangeEndValue: Int = 100
        super.onCreate(savedInstanceState)
        setContent {
            setContentView(R.layout.activity_game)
            var myText = findViewById<TextView>(R.id.textView2)
            rangeBeginValue = intent.getStringExtra("rangeBeginValue").toString().toInt()
            rangeEndValue = intent.getStringExtra("rangeEndValue").toString().toInt()
            val yesButton = findViewById<Button>(R.id.yesButton)
            val noButton = findViewById<Button>(R.id.noButton)
            var middle = 0



            middle = rangeBeginValue + (rangeEndValue - rangeBeginValue) / 2
            myText.text = "Ваше число больше $middle ?"

            yesButton.setOnClickListener {
                rangeBeginValue = middle
                middle = (rangeBeginValue + rangeEndValue) / 2
                myText.text = "Ваше число больше $middle ?"
                if (rangeBeginValue - rangeEndValue == -1){
                    myText.text = "Ваше число $rangeBeginValue ?"
                    yesButton.setOnClickListener {
                        finish()
                        Toast.makeText(this@Game, "Ваше число $rangeBeginValue", Toast.LENGTH_SHORT)
                            .show()
                    }
                    noButton.setOnClickListener {
                        finish()
                        Toast.makeText(this@Game, "Ваше число $rangeEndValue", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            noButton.setOnClickListener {
                rangeEndValue = middle
                middle = (rangeBeginValue + rangeEndValue) / 2
                myText.text = "Ваше число больше $middle ?"
                if (rangeBeginValue - rangeEndValue == -1){
                    myText.text = "Ваше число $rangeBeginValue ?"
                    yesButton.setOnClickListener {
                        Toast.makeText(this@Game, "Ваше число $rangeBeginValue", Toast.LENGTH_SHORT)
                            .show()
                    }
                    noButton.setOnClickListener {
                        Toast.makeText(this@Game, "Ваше число $rangeEndValue", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }


        }
    }

}