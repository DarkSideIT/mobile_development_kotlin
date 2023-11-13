package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.view.View

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numA = findViewById<EditText>(R.id.numA)
        val numB = findViewById<EditText>(R.id.numB)
        val button = findViewById<Button>(R.id.btnSum)
        val result = findViewById<TextView>(R.id.result)

        button.setOnClickListener(View.OnClickListener {
            val inputA = numA.text.toString()
            val inputB = numB.text.toString()

            try {
                if (inputA.isNotEmpty() && inputB.isNotEmpty()) {
                    val sumResult = inputA.toDouble() + inputB.toDouble()
                    result.text = "Результат сложения: $sumResult"
                } else {
                    result.text = "Введите числа A и B"
                }
            } catch (e: NumberFormatException) {
                result.text = "Неверный формат числа"
            }
        })
    }
}

