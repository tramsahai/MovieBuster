package com.example.moviebuster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // PARALLEL ARRAYS: Store movie data in separate but connected arrays
    // Like columns in a spreadsheet - all data at same index belongs to same movie
    private val movieTitles = arrayOf("The Godfather", "The Dark Knight", "Pulp Fiction")
    private val movieDirectors = arrayOf("Francis Ford Coppola", "Christopher Nolan", "Quentin Tarantino")
    private val userRatings = arrayOf(5.0, 5.0, 4.0)
    private val movieComments = arrayOf(
        "A masterpiece of cinema",
        "Epic superhero film with iconic Joker performance",
        "Quirky and captivating"
    )

    // These variables will connect to our screen elements (buttons and text views)
    private lateinit var tvAverageRating: TextView  // Will display the average rating
    private lateinit var btnAddMovie: Button        // Button to add new movies
    private lateinit var btnViewMovies: Button      // Button to view all movies

    // This function runs when the main screen is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Load the main screen layout

        // Connect our code to the screen layout elements
        initializeViews()

        // Make buttons work when clicked
        setupClickListeners()

        // Calculate and show average rating of all movies
        calculateAndDisplayAverageRating()
    }

    // This function finds all the buttons and text views on screen and connects them to our variables
    private fun initializeViews() {
        tvAverageRating = findViewById(R.id.tvAverageRating)  // Find the average rating text view
        btnAddMovie = findViewById(R.id.btnAddMovie)          // Find the Add Movie button
        btnViewMovies = findViewById(R.id.btnViewMovies)      // Find the View Movies button
    }

    // This function makes buttons do something when clicked
    private fun setupClickListeners() {
        // When Add Movie button is clicked
        btnAddMovie.setOnClickListener {
            // Create an intent to go to the Add Movie screen
            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)  // Actually go to the new screen
        }

        // When View Movies button is clicked
        btnViewMovies.setOnClickListener {
            // Create an intent to go to the Movie List screen
            val intent = Intent(this, MovieListActivity::class.java)
            startActivity(intent)  // Actually go to the new screen
        }
    }

    // This function calculates the average rating of all movies using a loop
    private fun calculateAndDisplayAverageRating() {
        // Check if we have any ratings to calculate (avoid division by zero)
        if (userRatings.isNotEmpty()) {
            var totalRating = 0.0  // Start with zero total

            // Loop through all ratings and add them up one by one
            // userRatings.indices gives us 0, 1, 2 (the positions in the array)
            for (i in userRatings.indices) {
                totalRating += userRatings[i]  // Add each rating to the total
            }

            // Calculate average by dividing total by number of movies
            val averageRating = totalRating / userRatings.size

            // Display the average on screen with one decimal place
            // "%.1f".format() makes sure we show only one digit after decimal point
            tvAverageRating.text = "Average Rating: ${"%.1f".format(averageRating)}/5"
        } else {
            // If no movies exist, show this message instead
            tvAverageRating.text = "No ratings yet"
        }
    }
}