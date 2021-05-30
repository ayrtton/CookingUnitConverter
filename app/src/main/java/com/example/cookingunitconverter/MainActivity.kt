package com.example.cookingunitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.cookingunitconverter.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillSpinner(binding.unitsConvertFrom)
        fillSpinner(binding.unitsConvertTo)

        binding.convertButton.setOnClickListener { convertUnit() }
    }

    private fun convertUnit() {
        val stringInTextField = binding.value.text.toString()
        val value = stringInTextField.toDouble()
        val convertFrom = binding.unitsConvertFrom.selectedItem.toString()
        val convertTo = binding.unitsConvertTo.selectedItem.toString()

        //Convert input to cups
        var conversionAux = conversionAux(convertFrom)
        val valueInCups = value / conversionAux

        //Convert output to the desired unit
        conversionAux = conversionAux(convertTo)
        var result = valueInCups * conversionAux

        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp) {
            result = kotlin.math.ceil(result)
        }

        val formattedResult = NumberFormat.getInstance().format(result)
        binding.result.text = getString(R.string.result, formattedResult)
    }

    private fun conversionAux(unit: String): Double {
        return when(unit) {
            "Cups" -> 1.0
            "Fluid Ounces" -> 8.0
            "Tablespoons" -> 16.0
            "Teaspoons" -> 48.0
            "Pints" -> 0.5
            "Quarts" -> 0.25
            "Gallons" -> 0.0625
            "Milliliters" -> 240.0
            else -> 1.0
        }
    }

    private fun fillSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            this, R.array.units_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}