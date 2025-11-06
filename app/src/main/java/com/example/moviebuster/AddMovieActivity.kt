package com.example.moviebuster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddMovieActivity : AppCompatActivity() {

    private lateinit var etMovieTitle: EditText
    private lateinit var etDirector: EditText
    private lateinit var etRating: EditText
    private lateinit var etComment: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        etMovieTitle = findViewById(R.id.etMovieTitle)
        etDirector = findViewById(R.id.etDirector)
        etRating = findViewById(R.id.etRating)
        etComment = findViewById(R.id.etComment)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
    }

    private fun setupClickListeners() {
        btnSave.setOnClickListener {
            if (validateInput()) {
                saveMovie()
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun validateInput(): Boolean {
        val title = etMovieTitle.text.toString().trim()
        val director = etDirector.text.toString().trim()
        val ratingText = etRating.text.toString().trim()
        val comment = etComment.text.toString().trim()

        if (title.isEmpty()) {
            etMovieTitle.error = "Movie title is required"
            return false
        }

        if (director.isEmpty()) {
            etDirector.error = "Director name is required"
            return false
        }

        if (ratingText.isEmpty()) {
            etRating.error = "Rating is required"
            return false
        }

        val rating = ratingText.toDoubleOrNull()
        if (rating == null || rating < 1 || rating > 5) {
            etRating.error = "Please enter rating between 1 and 5"
            return false
        }

        if (comment.isEmpty()) {
            etComment.error = "Please enter a comment"
            return false
        }

        return true
    }

    private fun saveMovie() {
        val title = etMovieTitle.text.toString().trim()
        val director = etDirector.text.toString().trim()
        val rating = etRating.text.toString().trim().toDouble()
        val comment = etComment.text.toString().trim()

        Toast.makeText(this, "Movie '$title' added successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}