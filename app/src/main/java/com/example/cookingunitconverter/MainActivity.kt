package com.example.cookingunitconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingunitconverter.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
        val value = stringInTextField.toDoubleOrNull()
        if(value == null) {
            binding.result.text = ""
            return
        }

        val convertFrom = binding.unitsConvertFrom.selectedItem.toString()
        val convertTo = binding.unitsConvertTo.selectedItem.toString()

        //Convert input to Cups (US)
        var conversionAux = conversionAux(convertFrom)
        val valueInCups = value / conversionAux

        //Convert output to the desired unit
        conversionAux = conversionAux(convertTo)
        var result = valueInCups * conversionAux

        if(binding.roundUpSwitch.isChecked) {
            result = kotlin.math.ceil(result)
        }

        val formattedResult = NumberFormat.getInstance().format(result)
        binding.result.text = getString(R.string.result, formattedResult)
    }

    private fun conversionAux(unit: String): Double {
        return when(unit) {
                "Cups (US)" -> 1.0
                "Cups (Metric)" -> 0.9464
                "Cups (Imperial)" -> 0.8327
                "Deciliters [dL]" -> 2.3659
                "Fluid Ounces (UK) [fl oz]" -> 8.3267
                "Fluid Ounces (US) [fl oz]" -> 8.0
                "Gallons (UK) [gal]" -> 0.052
                "Gallons (US)" -> 0.0625
                "Liters [L]" -> 0.2366
                "Milliliters [mL]" -> 236.5882
                "Pints (UK) [pt]" -> 0.4163
                "Pints (US) [pt]" -> 0.5
                "Quarts (UK) [qt]" -> 0.2082
                "Quarts (US) [qt]" -> 0.25
                "Tablespoons (US)" -> 16.0
                "Tablespoons (Metric)" -> 15.7725
                "Tablespoons (Imperial)" -> 13.3228
                "Teaspoons (US)" -> 48.0
                "Teaspoons (Metric)" -> 47.3176
                "Teaspoons (Imperial)" -> 39.9683
                else -> 0.0
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