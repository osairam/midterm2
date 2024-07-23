package com.example.midtermapp2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var selectedCategory: String
    private val quotes = mapOf(
        "Motivation" to listOf(
            "The only way to do great work is to love what you do. - Steve Jobs",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The best way to predict the future is to create it. - Peter Drucker"
        ),
        "Love" to listOf(
            "Love is composed of a single soul inhabiting two bodies. - Aristotle",
            "To love and be loved is to feel the sun from both sides. - David Viscott",
            "The best thing to hold onto in life is each other. - Audrey Hepburn"
        ),
        "Humor" to listOf(
            "I'm not arguing, I'm just explaining why I'm right.",
            "I'm on a seafood diet. I see food and I eat it.",
            "Why don’t scientists trust atoms? Because they make up everything!"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerCategories = findViewById<Spinner>(R.id.spinnerCategories)
        val btnShowQuote = findViewById<Button>(R.id.btnShowQuote)
        val tvQuote = findViewById<TextView>(R.id.tvQuote)

        // List of categories
        val categories = quotes.keys.toList()

        // Setting up the spinner with an ArrayAdapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategories.adapter = adapter

        // Spinner ItemSelectedListener
        spinnerCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedCategory = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Button OnClickListener
        btnShowQuote.setOnClickListener {
            val categoryQuotes = quotes[selectedCategory]
            val randomQuote = categoryQuotes?.get(Random.nextInt(categoryQuotes.size))
            tvQuote.text = randomQuote
        }
    }
}
