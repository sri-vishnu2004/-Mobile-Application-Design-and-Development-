package com.example.myapplication

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var studentCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupFontColorTask()
        setupTechLoungeTask()
        setupBMITask()
        setupTempConverterTask()
    }

    private fun setupFontColorTask() {
        val tvFontColor = findViewById<TextView>(R.id.tvFontColor)
        val btnChangeStyle = findViewById<Button>(R.id.btnChangeStyle)

        btnChangeStyle.setOnClickListener {
            tvFontColor.setTextColor(Color.RED)
            tvFontColor.setTypeface(null, Typeface.BOLD_ITALIC)
            Toast.makeText(this, "Font and Color Changed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupTechLoungeTask() {
        val tvStudentCount = findViewById<TextView>(R.id.tvStudentCount)
        val btnCheckIn = findViewById<Button>(R.id.btnCheckIn)
        val btnCheckOut = findViewById<Button>(R.id.btnCheckOut)

        btnCheckIn.setOnClickListener {
            studentCount++
            tvStudentCount.text = "Students inside: $studentCount"
        }

        btnCheckOut.setOnClickListener {
            if (studentCount > 0) {
                studentCount--
                tvStudentCount.text = "Students inside: $studentCount"
            } else {
                Toast.makeText(this, "No students to check out", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupBMITask() {
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val btnCalculateBMI = findViewById<Button>(R.id.btnCalculateBMI)
        val tvBMIResult = findViewById<TextView>(R.id.tvBMIResult)

        btnCalculateBMI.setOnClickListener {
            val weightStr = etWeight.text.toString()
            val heightStr = etHeight.text.toString()

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                val weight = weightStr.toFloat()
                val height = heightStr.toFloat()
                if (height > 0) {
                    val bmi = weight / (height * height)
                    tvBMIResult.text = String.format("BMI Result: %.2f", bmi)
                } else {
                    tvBMIResult.text = "Height must be greater than 0"
                }
            } else {
                Toast.makeText(this, "Please enter weight and height", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupTempConverterTask() {
        val etTemp = findViewById<EditText>(R.id.etTemp)
        val btnConvertTemp = findViewById<Button>(R.id.btnConvertTemp)
        val tvTempResult = findViewById<TextView>(R.id.tvTempResult)

        btnConvertTemp.setOnClickListener {
            val tempStr = etTemp.text.toString()
            if (tempStr.isNotEmpty()) {
                val celsius = tempStr.toFloat()
                val fahrenheit = (celsius * 9 / 5) + 32
                tvTempResult.text = String.format("Result: %.2f °F", fahrenheit)
            } else {
                Toast.makeText(this, "Please enter temperature", Toast.LENGTH_SHORT).show()
            }
        }
    }
}