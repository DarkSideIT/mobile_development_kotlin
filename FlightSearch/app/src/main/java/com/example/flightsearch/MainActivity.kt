package com.example.flightsearch

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
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
import com.example.flightsearch.ui.theme.FlightSearchTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {

    private lateinit var departureDate: EditText
    private lateinit var arrivalDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        departureDate = findViewById(R.id.departureDate)
        arrivalDate = findViewById(R.id.arrivalDate)


        departureDate.setOnClickListener{
            showDatePickerDialog(departureDate)
        }

        arrivalDate.setOnClickListener{
            showDatePickerDialog(arrivalDate)
        }
    }

    fun showDatePickerDialog(editText: EditText ){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener{ _, year, monthOfYear, dayOfMonth ->
                editText.setText("$dayOfMonth/${monthOfYear+1}/$year")
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}

