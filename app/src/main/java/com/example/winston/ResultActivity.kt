package com.example.winston

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val totalTips = intent.getDoubleExtra("TOTAL_TIPS", 0.0)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        textViewResult.text = String.format("Total de Propinas: %.2f", totalTips)
    }
}