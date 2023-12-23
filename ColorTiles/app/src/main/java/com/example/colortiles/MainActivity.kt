package com.example.colortiles

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.graphics.Color
import android.widget.LinearLayout
import android.graphics.drawable.ColorDrawable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.colortiles.ui.theme.ColorTilesTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var mainGridLayout: GridLayout
    private val tilesState = Array(4) { BooleanArray(4) } // true - светлый цвет, false - тёмный цвет

    private var isGameWon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainGridLayout = findViewById(R.id.mainGridLayout)


        initializeTiles()
    }

    private fun initializeTiles() {
        val random = Random.Default

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val isLightColor = random.nextBoolean()
                tilesState[i][j] = isLightColor

                val tile = mainGridLayout.findViewWithTag<View>("$i$j")
                tile.setBackgroundColor(if (isLightColor) Color.WHITE else Color.BLACK)
            }
        }
    }

    fun onClick(view: View) {
        val tag = view.tag.toString()
        val row = tag.substring(0, 1).toInt()
        val col = tag.substring(1, 2).toInt()


        toggleTileColor(view)


        for (i in 0 until 4) {
            val tile = mainGridLayout.findViewWithTag<View>("$i$col")
            toggleTileColor(tile)
        }


        for (j in 0 until 4) {
            val tile = mainGridLayout.findViewWithTag<View>("$row$j")
            toggleTileColor(tile)
        }

        checkForWin()
        if (isGameWon){
            showToast("Вы выиграли!")
        }

    }

    private fun toggleTileColor(tile: View) {
        val currentColor = (tile.background.current as? ColorDrawable)?.color ?: Color.BLACK
        val newColor = if (currentColor == Color.WHITE) Color.BLACK else Color.WHITE
        tile.setBackgroundColor(newColor)
    }


    private fun checkForWin() {
        val firstColor = tilesState[0][0]

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (tilesState[i][j] != firstColor) {

                    return
                }
            }
        }
        isGameWon = true
        return
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
