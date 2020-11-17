package ru.android.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener { this.finish() }
    }
}