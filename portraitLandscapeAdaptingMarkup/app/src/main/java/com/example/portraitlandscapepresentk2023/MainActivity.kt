package com.example.portraitlandscapepresentk2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    lateinit var adapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = ArrayAdapter.createFromResource(this, R.array.pictures, R.layout.item)


        val spinner = findViewById<Spinner>(R.id.pictures_list)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this


        val initialImageName = resources.getStringArray(R.array.pictures)[0]
        val initialImageResourceId = resources.getIdentifier(initialImageName, "drawable", packageName)
        val iv = findViewById<ImageView>(R.id.picture)
        iv.setImageResource(initialImageResourceId)
    }

    fun onChangePictureClick(v: View) {
        val iv = findViewById<ImageView>(R.id.picture)
        val spinner = findViewById<Spinner>(R.id.pictures_list)


        val currentPosition = spinner.selectedItemPosition


        val picturesArray = resources.getStringArray(R.array.pictures)


        val nextPosition = (currentPosition + 1) % picturesArray.size


        val imageName = picturesArray[nextPosition]
        val imageResourceId = resources.getIdentifier(imageName, "drawable", packageName)
        iv.setImageResource(imageResourceId)


        spinner.setSelection(nextPosition)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val iv = findViewById<ImageView>(R.id.picture)


        val picturesArray = resources.getStringArray(R.array.pictures)

        
        val imageName = picturesArray[position]
        val imageResourceId = resources.getIdentifier(imageName, "drawable", packageName)
        iv.setImageResource(imageResourceId)

        Toast.makeText(this, "выбран элемент $position", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "не выбран элемент", Toast.LENGTH_SHORT ).show()
    }
}