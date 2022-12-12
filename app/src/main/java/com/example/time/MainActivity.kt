package com.example.time

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.time.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }
    private fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost==null){
            binding.tipResult.text = ""
            return
        }

        val selectedID = binding.tipOption.checkedRadioButtonId
        val tipPercentage = when(selectedID){
            R.id.option_eighteen_percentage -> 0.18
            R.id.option_twenty_percent -> 0.20
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUP = binding.roundUpSwitch.isChecked
        if (roundUP) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)




    }
}