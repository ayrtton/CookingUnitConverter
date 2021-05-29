package com.example.cookingunitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillSpinner(R.id.units_convert_from)
        fillSpinner(R.id.units_convert_to)
    }

    private fun fillSpinner(spinner: Int) {
        val spinner: Spinner = findViewById(spinner)
        ArrayAdapter.createFromResource(
            this, R.array.units_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}